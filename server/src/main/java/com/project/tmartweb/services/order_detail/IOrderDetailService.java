package com.project.tmartweb.services.order_detail;

import com.project.tmartweb.models.dtos.OrderDetailDTO;
import com.project.tmartweb.models.entities.OrderDetail;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IOrderDetailService extends IBaseService<OrderDetail, OrderDetailDTO, UUID> {
}
