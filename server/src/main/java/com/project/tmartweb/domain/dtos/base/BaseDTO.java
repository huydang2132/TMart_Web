package com.project.tmartweb.domain.dtos.base;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseDTO {
    private String createdBy;

    private String updatedBy;
}
