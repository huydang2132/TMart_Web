package com.project.tmartweb.application.services.order;

import com.project.tmartweb.application.repositories.*;
import com.project.tmartweb.application.responses.MailOrder;
import com.project.tmartweb.application.responses.VNPayResponse;
import com.project.tmartweb.application.services.cart.CartService;
import com.project.tmartweb.application.services.coupon.CouponService;
import com.project.tmartweb.application.services.email.IEmailService;
import com.project.tmartweb.application.services.payment.VNPayService;
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
import jakarta.servlet.http.HttpServletRequest;
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
    private final NotificationRepository notificationRepository;
    private final VNPayService vnpayService;
    private final IEmailService emailService;

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
        Notification notification = new Notification();
        notification.setOrder(order);
        notification.setTitle("Đơn hàng đã tạo thành công");
        notification.setContent("Đơn hàng " + order.getId() + " đã được tạo thành công. " +
                " Bạn có thể theo dõi đơn hàng tại đây.");
        notification.setUser(user);
        notificationRepository.save(notification);
        order.setTotalMoney(Calculator.totalMoneyOrder(orderDetails, discount));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(UUID id, OrderDTO orderDTO) {
        try {
            Order order = getById(id);
            order.setStatus(orderDTO.getStatus());
            Notification notification = new Notification();
            notification.setUser(order.getUser());
            notification.setOrder(order);
            if (orderDTO.getStatus() == OrderStatus.PROCESSED) {
                notification.setTitle("Đơn hàng đã xử lý thành công.");
                notification.setContent("Đơn hàng của bạn đã được xử lý thành công. " +
                        " Chúng tôi sẽ giao cho đơn vị vận chuyển trong thời gian sớm nhất.");
            }
            if (orderDTO.getStatus() == OrderStatus.SHIPPING) {
                notification.setTitle("Đơn hàng đang được giao.");
                notification.setContent("Đơn hàng của bạn đã được giao cho đơn vị vận chuyển. " +
                        " Hãy chú ý điện thoại nhé, đơn hàng sẽ được giao tới bạn trong thời gian sớm nhất có thể.");
            }
            if (orderDTO.getStatus() == OrderStatus.SHIPPED) {
                notification.setTitle("Đơn hàng đã giao thành công.");
                notification.setContent("Đơn hàng của bạn đã được giao thành công. " +
                        " Hãy đánh trải nghiệm, đánh giá sản phẩm và nếu có lỗi gì hãy liên hệ với chúng tôi ngay nhé.");
                emailService.sendEmail(order.getUser().getEmail(), "Đơn hàng", MailOrder.orderShipped());
            }
            if (orderDTO.getStatus() == OrderStatus.CANCELLED) {
                notification.setTitle("Đơn hàng đã hủy thành công.");
                notification.setContent("Đơn hàng của bạn đã được hủy thành công. ");
            }
            notificationRepository.save(notification);
            if (orderDTO.getAddress() != null) {
                order.setAddress(orderDTO.getAddress());
            }
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public List<Order> findByUserId(UUID userId, OrderStatus status, String keyword) {
        return orderRepository.findByUserId(userId, status, keyword);
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

    @Override
    public void FeedbackOrder(UUID orderId) {
        Order order = getById(orderId);
        order.setFeedback(true);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public VNPayResponse createOrder(OrderDTO orderDTO, HttpServletRequest request) {
        Order order = insert(orderDTO);
        String urlPayment = "";
        try {
            emailService.sendEmail(order.getUser().getEmail(), "Đơn hàng", MailOrder.orderSuccess());
            if (order.getPaymentMethod().equals("VNPAY")) {
                urlPayment = vnpayService.createOrder((int) order.getTotalMoney(), String.valueOf(order.getId()), request);
                order.setStatus(OrderStatus.UNPAID);
                orderRepository.save(order);
            }
            return new VNPayResponse("VNPay", urlPayment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int orderReturn(HttpServletRequest request) {
        int code = vnpayService.orderReturn(request);
        Order order = getById(UUID.fromString(request.getParameter("vnp_OrderInfo")));
        if (code == 1) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.UNPAID);
        }
        orderRepository.save(order);
        return code;
    }
}
