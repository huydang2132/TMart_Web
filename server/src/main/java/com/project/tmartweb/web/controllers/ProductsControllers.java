package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.ProductDTO;
import com.project.tmartweb.domain.entities.Product;
import com.project.tmartweb.services.product.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductsControllers {
    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(
            @RequestParam("page") Integer page,
            @RequestParam("perPage") Integer perPage) {
        return ResponseEntity.ok(iProductService.getAll(page, perPage));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable UUID id,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("perPage") int perPage) {
        var result = iProductService.getAllProductsByCategory(id, page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(iProductService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> insertProduct(
            @Valid @RequestPart("product") ProductDTO productDTO,
            @RequestPart("files") List<MultipartFile> files) {
        try {
            productDTO.setFiles(files);
            Product product = iProductService.insert(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) {
        var result = iProductService.update(id, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        Product product = iProductService.getById(id);
        iProductService.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Delete success");
    }
}
