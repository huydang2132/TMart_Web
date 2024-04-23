package com.project.tmartweb.services.product;

import com.project.tmartweb.domain.dtos.ImageProductDTO;
import com.project.tmartweb.domain.dtos.ProductDTO;
import com.project.tmartweb.domain.entities.Category;
import com.project.tmartweb.domain.entities.ImageProduct;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.Pagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.InvalidParamException;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.repositories.ImageProductRepository;
import com.project.tmartweb.repositories.ProductRepository;
import com.project.tmartweb.services.category.CategoryService;
import com.project.tmartweb.services.file.IFileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final IFileService fileService;

    @Override
    public PaginationDTO<Product> getAllProductsByCategory(UUID categoryId, Integer page, Integer perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage, Sort.by("createdAt").descending());
        Page<Product> pageData = productRepository.findAllByCategory_IdAndDeleted(categoryId, false, pageRequest);
        BasePagination<Product, ProductRepository> pagination = new BasePagination<>(productRepository);
        return pagination.paginate(page, perPage, pageData);
    }

    @Override
    public Product insert(ProductDTO productDTO) {
        Category category = categoryService.getById(productDTO.getCategoryId());
        Product product = mapper.map(productDTO, Product.class);
        product.setCategory(category);
        product.setCreatedBy(productDTO.getCreatedBy());
        for (MultipartFile file : productDTO.getFiles()) {
            String url = fileService.uploadFile(file);
            ImageProduct imageProduct = new ImageProduct();
            imageProduct.setUrl(url);
            imageProduct.setProduct(product);
            imageProductRepository.save(imageProduct);
        }

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
    public PaginationDTO<Product> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(productRepository.findAll(), null);
        }
        Page<Product> pageData = productRepository.findAllByDeleted(false,
                PageRequest.of(page, perPage, Sort.by("createdAt").descending()));
        Pagination pagination = new Pagination(page, perPage, pageData.getTotalPages() - 1,
                pageData.getTotalElements());
        return new PaginationDTO<>(pageData.getContent(), pagination);
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
    public int deleteMultiple(List<UUID> ids) {
        return 0;
    }
}
