package com.project.tmartweb.services.product;

import com.project.tmartweb.models.dtos.ImageProductDTO;
import com.project.tmartweb.models.entities.ImageProduct;
import com.project.tmartweb.services.base.IBaseService;
import com.project.tmartweb.services.base.IBaseServiceMultiple;
import com.project.tmartweb.models.dtos.ProductDTO;
import com.project.tmartweb.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;


public interface IProductService
        extends IBaseService<Product, ProductDTO, UUID>,
        IBaseServiceMultiple<Product, ProductDTO> {
    Page<Product> getAllProductsPageRequest(PageRequest pageRequest);

    ImageProduct insertImageProduct(UUID productId, ImageProductDTO imageProductDTO);
}
