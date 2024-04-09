package com.project.tmartweb.models.entities;

import com.project.tmartweb.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {
    @Column(name = "price")
    @Min(0)
    private double price;

    @Column(name = "quantity")
    @Min(1)
    private int quantity;

    @Column(name = "total_money")
    @Min(0)
    private double totalMoney;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
