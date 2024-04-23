package com.project.tmartweb.services.cart;

import com.project.tmartweb.domain.dtos.CartDTO;
import com.project.tmartweb.domain.entities.Cart;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.services.base.IBaseService;
import com.project.tmartweb.services.base.IBaseServiceMultiple;

import java.util.UUID;

public interface ICartService extends IBaseService<Cart, CartDTO, UUID>,
        IBaseServiceMultiple<Cart, CartDTO, UUID> {
    PaginationDTO<Cart> getAllByUser(UUID userId, Integer page, Integer perPage);
}
