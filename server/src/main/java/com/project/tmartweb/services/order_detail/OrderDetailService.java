package com.project.tmartweb.services.order_detail;

import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.helpers.Calculator;
import com.project.tmartweb.models.dtos.OrderDetailDTO;
import com.project.tmartweb.models.entities.Order;
import com.project.tmartweb.models.entities.OrderDetail;
import com.project.tmartweb.models.entities.Product;
import com.project.tmartweb.repositories.OrderDetailRepository;
import com.project.tmartweb.repositories.OrderRepository;
import com.project.tmartweb.services.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final IProductService productService;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public OrderDetail insert(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = mapper.map(orderDetailDTO, OrderDetail.class);
        Product product = productService.getById(orderDetailDTO.getProductId());
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new NotFoundException("Đơn hàng không tồn taị!", "Order not found"));
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail.setTotalMoney(Calculator.totalMoney(orderDetail.getPrice(), orderDetail.getQuantity()));
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(UUID id, OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = getById(id);
        mapper.map(orderDetailDTO, orderDetail);
        Product product = productService.getById(orderDetailDTO.getProductId());
        Order order = orderRepository.getById(orderDetailDTO.getOrderId());
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(OrderDetail orderDetail) {
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(UUID id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail getById(UUID id) {
        return findById(id).
                orElseThrow(() -> new NotFoundException("Chi tiết đơn hàng không tồn tại!", "Order detail not found"));
    }
}