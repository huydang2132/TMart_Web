package com.project.tmartweb.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.tmartweb.domain.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    private String title;

    private double price;

    private double discount;

    private String description;

    @Column(name = "quantity")
    private int quantity;

    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProducts;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks;
}
