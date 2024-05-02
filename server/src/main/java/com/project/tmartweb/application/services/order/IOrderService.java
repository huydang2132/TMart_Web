package com.project.tmartweb.application.services.order;

import com.project.tmartweb.application.services.base.IBaseService;
import com.project.tmartweb.domain.dtos.OrderDTO;
import com.project.tmartweb.domain.entities.Cart;
import com.project.tmartweb.domain.entities.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderService extends IBaseService<Order, OrderDTO, UUID> {
    List<Order> findByUserId(UUID userId);

    double totalMoneyOrder(List<Cart> carts, double discount);
}
