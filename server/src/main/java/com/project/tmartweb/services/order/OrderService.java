package com.project.tmartweb.services.order;

import com.project.tmartweb.domain.dtos.OrderDTO;
import com.project.tmartweb.domain.dtos.OrderDetailDTO;
import com.project.tmartweb.domain.dtos.OrderItem;
import com.project.tmartweb.domain.entities.*;
import com.project.tmartweb.domain.enums.OrderStatus;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.helpers.Calculator;
import com.project.tmartweb.repositories.OrderRepository;
import com.project.tmartweb.repositories.ProductRepository;
import com.project.tmartweb.services.coupon.CouponService;
import com.project.tmartweb.services.order_detail.OrderDetailService;
import com.project.tmartweb.services.product.ProductService;
import com.project.tmartweb.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDetailService orderDetailService;
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
        double discount = coupon != null ? coupon.getDiscount() : 0;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderItem orderItem : orderDTO.getOrderItems()) {
            Product product = productService.getById(orderItem.getProductId());
            OrderDetailDTO orderDetailDTO = mapper.map(orderItem, OrderDetailDTO.class);
            orderDetailDTO.setOrderId(order.getId());
            orderDetails.add(orderDetailService.insert(orderDetailDTO));
            product.setQuantity(product.getQuantity() - orderItem.getQuantity());
            productRepository.save(product);
        }
        order.setCoupon(coupon);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalMoney(Calculator.totalMoneyOrder(orderDetails, discount));
        return order;
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
        return null;
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
}
