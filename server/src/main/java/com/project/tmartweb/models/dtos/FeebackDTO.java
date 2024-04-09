package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
import com.project.tmartweb.models.entities.Product;
import com.project.tmartweb.models.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
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
public class FeebackDTO extends BaseDTO {
    private String note;

    @Min(0)
    @Max(5)
    private int star = 0;

    private String image;

    private UUID userId;

    private UUID productId;
}
