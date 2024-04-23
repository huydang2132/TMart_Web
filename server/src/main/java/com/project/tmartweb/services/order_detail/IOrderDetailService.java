package com.project.tmartweb.services.order_detail;

import com.project.tmartweb.domain.dtos.OrderDetailDTO;
import com.project.tmartweb.domain.entities.OrderDetail;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IOrderDetailService extends IBaseService<OrderDetail, OrderDetailDTO, UUID> {
    PaginationDTO<OrderDetail> getAllByOrder(UUID id, Integer page, Integer perPage);
}
