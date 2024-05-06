package com.project.tmartweb.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("pending"),
    PROCESSED("processed"),
    SHIPPING("shipping"),
    SHIPPED("shipped"),
    CANCELLED("cancelled");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
