package com.project.tmartweb.services.order;

import com.project.tmartweb.enums.OrderStatus;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.models.dtos.OrderDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.models.entities.Order;
import com.project.tmartweb.models.entities.User;
import com.project.tmartweb.repositories.OrderRepository;
import com.project.tmartweb.services.coupon.CouponService;
import com.project.tmartweb.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CouponService couponService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public Order insert(OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO, Order.class);
        User user = userService.getById(orderDTO.getUserId());
        Coupon coupon = orderDTO.getCouponId() == null ? null : couponService.getById(orderDTO.getCouponId());
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCoupon(coupon);
        return null;
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
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by("createdAt").descending());
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
