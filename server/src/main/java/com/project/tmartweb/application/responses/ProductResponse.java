package com.project.tmartweb.application.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
