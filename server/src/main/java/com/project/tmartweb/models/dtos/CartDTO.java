package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO extends BaseDTO {
    @Min(1)
    private int quantity;

    private UUID userId;

    private UUID productId;
}
