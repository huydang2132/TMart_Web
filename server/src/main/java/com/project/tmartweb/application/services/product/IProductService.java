package com.project.tmartweb.application.services.product;

import com.project.tmartweb.application.services.base.IBaseService;
import com.project.tmartweb.application.services.base.IBaseServiceMultiple;
import com.project.tmartweb.domain.dtos.ProductDTO;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.domain.paginate.PaginationDTO;

import java.util.UUID;

public interface IProductService
        extends IBaseService<Product, ProductDTO, UUID>,
        IBaseServiceMultiple<Product, ProductDTO, UUID> {
    PaginationDTO<Product> getAllProductsByCategory(UUID categoryId, Integer page, Integer perPage);

    PaginationDTO<Product> getAllDeleted(Integer page, Integer perPage);
}
