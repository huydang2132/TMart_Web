package com.project.tmartweb.application.services.image;

import com.project.tmartweb.domain.entities.ImageProduct;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IImageProductService {
    List<ImageProduct> getByProductId(UUID productId);

    String uploadImages(UUID productId, List<MultipartFile> images);
}
