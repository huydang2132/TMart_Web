package com.project.tmartweb.domain.dtos;

import com.project.tmartweb.domain.dtos.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private int quantity;

    private String description;

    private Boolean deleted = Boolean.FALSE;
}
