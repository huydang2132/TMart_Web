package com.project.tmartweb.web.controllers;

import com.project.tmartweb.models.dtos.ImageProductDTO;
import com.project.tmartweb.models.entities.ImageProduct;
import com.project.tmartweb.services.product.IProductService;
import com.project.tmartweb.models.dtos.ProductDTO;
import com.project.tmartweb.models.entities.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductsControllers {
    @Autowired
    private IProductService iProductService;

    @GetMapping("gelAll")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(iProductService.getAll());
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProductsPageRequest(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        Page<Product> productsPage = iProductService.getAllProductsPageRequest(pageRequest);
        int totalPages = productsPage.getTotalPages();
        List<Product> products = productsPage.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(iProductService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> insertProduct(
            @Valid @RequestBody ProductDTO productDTO
    ) {
        try {
            Product product = iProductService.insert(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    //   Không sử dụng
    @PostMapping(value = "upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable UUID id,
            @ModelAttribute("files") List<MultipartFile> files
    ) {
        try {
            files = files == null ? new ArrayList<MultipartFile>() : files;
            Product product = iProductService.getById(id);
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                String fileName = null;
                fileName = storeFile(file);
                ImageProductDTO imageProductDTO = new ImageProductDTO();
                imageProductDTO.setUrl(fileName);
                ImageProduct imageProduct =
                        iProductService.insertImageProduct(product.getId(), imageProductDTO);
            }
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Success");
        } catch (IOException e) {
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

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String newFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), newFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return newFileName;
    }
}
