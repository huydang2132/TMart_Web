package com.project.tmartweb.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    

    private String title;

    private double price;

    private double discount;

    private String description;

    private Boolean deleted = Boolean.FALSE;

    private String classify;

    private UUID categoryId;
}
