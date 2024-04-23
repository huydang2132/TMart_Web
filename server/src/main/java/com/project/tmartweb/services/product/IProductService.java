package com.project.tmartweb.services.product;

import com.project.tmartweb.domain.dtos.ImageProductDTO;
import com.project.tmartweb.domain.dtos.ProductDTO;
import com.project.tmartweb.domain.entities.ImageProduct;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.services.base.IBaseService;
import com.project.tmartweb.services.base.IBaseServiceMultiple;

import java.util.UUID;

public interface IProductService
        extends IBaseService<Product, ProductDTO, UUID>,
        IBaseServiceMultiple<Product, ProductDTO, UUID> {
    PaginationDTO<Product> getAllProductsByCategory(UUID categoryId, Integer page, Integer perPage);
}
