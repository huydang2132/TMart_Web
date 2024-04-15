package com.project.tmartweb.services.product;

import com.project.tmartweb.exceptions.InvalidParamException;
import com.project.tmartweb.repositories.ImageProductRepository;
import com.project.tmartweb.repositories.ProductRepository;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.models.dtos.ImageProductDTO;
import com.project.tmartweb.models.dtos.ProductDTO;
import com.project.tmartweb.models.entities.Category;
import com.project.tmartweb.models.entities.ImageProduct;
import com.project.tmartweb.models.entities.Product;
import com.project.tmartweb.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;
    private final ImageProductRepository imageProductRepository;

    @Override
    public Page<Product> getAllProductsPageRequest(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Product insert(ProductDTO productDTO) {
        Category category = categoryService.getById(productDTO.getCategoryId());
        Product product = mapper.map(productDTO, Product.class);
        product.setCategory(category);
        product.setCreatedBy(productDTO.getCreatedBy());
        return productRepository.save(product);
    }

    @Override
    public Product update(UUID id, ProductDTO productDTO) {
        Product product = getById(id);
        Category category = categoryService.getById(productDTO.getCategoryId());
        mapper.map(productDTO, product);
        product.setCategory(category);
        product.setUpdatedBy(productDTO.getUpdatedBy());
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAllByDeletedOrderByCreatedAtDesc(false);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public List<Product> insertMultiple(List<ProductDTO> productDTOS) {

        return List.of();
    }

    @Override
    public List<Product> updateMultiple(List<ProductDTO> productDTOS) {
        return List.of();
    }

    @Override
    public List<Product> deleteMultiple(List<Product> products) {
        return List.of();
    }

    @Override
    public ImageProduct insertImageProduct(UUID productId, ImageProductDTO imageProductDTO) {
        Product product = getById(productId);
        ImageProduct imageProduct = mapper.map(imageProductDTO, ImageProduct.class);
        int size = imageProductRepository.findByProductId(productId).size();
        if (size >= 5) {
            throw new InvalidParamException("Do not have more than 5 image");
        }
        return imageProductRepository.save(imageProduct);
    }
}
