package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO {
    private UUID categoryId;

    private String title;

    private double price;

    private double discount;

    private String description;

    private Boolean deleted = Boolean.FALSE;

    private String classify;

    private List<MultipartFile> files;
}
