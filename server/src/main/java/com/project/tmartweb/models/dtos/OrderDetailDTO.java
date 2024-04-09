package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
import com.project.tmartweb.models.entities.Order;
import com.project.tmartweb.models.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class OrderDetailDTO extends BaseDTO {
    @Min(0)
    private double price;

    @Min(1)
    private int quantity;

    @Min(0)
    private double totalMoney;

    private UUID orderId;

    private UUID productId;
}
