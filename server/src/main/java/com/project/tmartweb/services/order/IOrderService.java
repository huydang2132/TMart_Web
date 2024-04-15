package com.project.tmartweb.services.order;

import com.project.tmartweb.models.dtos.OrderDTO;
import com.project.tmartweb.models.entities.Order;
import com.project.tmartweb.services.base.IBaseService;

import java.util.List;
import java.util.UUID;

public interface IOrderService extends IBaseService<Order, OrderDTO, UUID> {
    List<Order> findByUserId(UUID userId);
}
