package com.project.tmartweb.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("pending"),
    PROCESSING("processing"),
    SHIPPED("shipped"),
    CANCELLED("cancelled");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
