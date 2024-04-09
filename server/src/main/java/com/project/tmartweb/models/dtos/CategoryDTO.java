package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends BaseDTO {
    private String name;
}
