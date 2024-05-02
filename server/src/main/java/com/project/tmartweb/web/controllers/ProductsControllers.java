package com.project.tmartweb.web.controllers;

import com.project.tmartweb.application.services.image.IImageProductService;
import com.project.tmartweb.application.services.product.IProductService;
import com.project.tmartweb.domain.dtos.ProductDTO;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.web.base.RestAPI;
import com.project.tmartweb.web.base.RoleAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestAPI("${api.prefix}/products")
public class ProductsControllers {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IImageProductService iImageProductService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage) {
        return ResponseEntity.ok(iProductService.getAll(page, perPage));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable UUID id,
                                                     @RequestParam(name = "page", required = false) int page,
                                                     @RequestParam(name = "perPage", required = false) int perPage) {
        var result = iProductService.getAllProductsByCategory(id, page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(iProductService.getById(id));
    }

    @GetMapping("/deleted")
    @RoleAdmin
    public ResponseEntity<?> getAllDeletedProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage
    ) {
        var response = iProductService.getAllDeleted(page, perPage);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertProduct(
            @Valid @RequestBody ProductDTO productDTO) {
        Product product = iProductService.insert(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PostMapping("/upload-image/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadImage(
            @PathVariable UUID id,
            @RequestPart("images") List<MultipartFile> files
    ) {
        String result = iImageProductService.uploadImages(id, files);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        var result = iProductService.update(id, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        Product product = iProductService.getById(id);
        iProductService.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Delete success");
    }
}
