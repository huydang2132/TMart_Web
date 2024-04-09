package com.project.tmartweb.enums;

import lombok.Getter;

@Getter
public enum RoleId {
    ADMIN("admin"),
    USER("user");
    private final String roleName;

    RoleId(String roleName) {
        this.roleName = roleName;
    }
}
