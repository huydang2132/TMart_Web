package com.project.tmartweb.models.entities;

import com.project.tmartweb.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "galeries")
public class Galery extends BaseEntity {
    @Column(name = "image", length = 100)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
