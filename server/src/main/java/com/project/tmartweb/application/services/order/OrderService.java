package com.project.tmartweb.application.services.order;

import com.project.tmartweb.application.repositories.CouponRepository;
import com.project.tmartweb.application.repositories.OrderDetailRepository;
import com.project.tmartweb.application.repositories.OrderRepository;
import com.project.tmartweb.application.repositories.ProductRepository;
import com.project.tmartweb.application.services.cart.CartService;
import com.project.tmartweb.application.services.coupon.CouponService;
import com.project.tmartweb.application.services.product.ProductService;
import com.project.tmartweb.application.services.user.UserService;
import com.project.tmartweb.config.exceptions.InvalidParamException;
import com.project.tmartweb.config.exceptions.NotFoundException;
import com.project.tmartweb.config.helpers.Calculator;
import com.project.tmartweb.domain.dtos.CartDTO;
import com.project.tmartweb.domain.dtos.OrderDTO;
import com.project.tmartweb.domain.entities.*;
import com.project.tmartweb.domain.enums.OrderStatus;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final OrderDetailRepository orderDetailRepository;
    private final CouponRepository couponRepository;
    private final CouponService couponService;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Order insert(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);
        User user = userService.getById(orderDTO.getUserId());
        Coupon coupon = orderDTO.getCouponId() == null ? null : couponService.useCoupon(orderDTO.getCouponId());
        if (coupon != null) {
            coupon.setQuantity(coupon.getQuantity() - 1);
            couponRepository.save(coupon);
        }
        double discount = coupon == null ? 0 : coupon.getDiscount();
        order.setCreatedAt(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
        orderRepository.save(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartDTO cartDTO : orderDTO.getCartItems()) {
            Product product = productService.getById(cartDTO.getProductId());
            if (product.getQuantity() < cartDTO.getQuantity()) {
                throw new InvalidParamException("Số lượng sản phẩm trong kho không đủ", "Quantity not enough");
            }
            OrderDetail orderDetail = mapper.map(cartDTO, OrderDetail.class);
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setPrice(product.getDiscount() == 0 ?
                    product.getPrice() :
                    product.getPrice() * (100 - product.getDiscount()) / 100);
            orderDetail.setTotalMoney(Calculator.totalMoney(product.getPrice(), cartDTO.getQuantity(), product.getDiscount()));
            orderDetails.add(orderDetailRepository.save(orderDetail));
            Cart cart = cartService.getById(cartDTO.getId());
            cartService.delete(cart);
            product.setQuantity(product.getQuantity() - cartDTO.getQuantity());
            productRepository.save(product);
        }
        order.setCoupon(coupon);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalMoney(Calculator.totalMoneyOrder(orderDetails, discount));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(UUID id, OrderDTO orderDTO) {
        Order order = getById(id);
        order.setStatus(orderDTO.getStatus());
        order.setAddress(orderDTO.getAddress());
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public PaginationDTO<Order> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(orderRepository.findAll(
                    Sort.by("createdAt").descending()),
                    null);
        }
        BasePagination<Order, OrderRepository> basePagination = new BasePagination<>(orderRepository);
        return basePagination.paginate(page, perPage);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Đơn hàng không tôn tại!", "Order not found"));
    }

    @Override
    public List<Order> findByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public double totalMoneyOrder(List<Cart> carts, double discount) {
        double total = 0;
        for (Cart orderItem : carts) {
            total += Calculator.totalMoney(orderItem.getProduct().getPrice(), orderItem.getQuantity(), orderItem.getProduct().getDiscount());
        }
        total = total * (100 - discount) / 100;
        return total;
    }
}
