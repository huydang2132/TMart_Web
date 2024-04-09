package com.project.tmartweb.services;

import com.project.tmartweb.models.dtos.OrderDTO;
import com.project.tmartweb.models.entities.Order;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IOrderService extends IBaseService<Order, OrderDTO, UUID> {
}
