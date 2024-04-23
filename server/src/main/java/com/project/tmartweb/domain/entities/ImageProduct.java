package com.project.tmartweb.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.tmartweb.domain.entities.base.BaseEntity;
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
@Table(name = "image_product")
public class ImageProduct extends BaseEntity {
    @Column(name = "image_name", length = 100)
    private String imageName;

    @Column(name = "url", length = 100)
    private String url;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
