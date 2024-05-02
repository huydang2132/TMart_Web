package com.project.tmartweb.domain.entities;

import com.project.tmartweb.domain.entities.base.BaseEntity;
import com.project.tmartweb.domain.enums.OrderStatus;
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
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "note", length = 200)
    private String note;

    @Column(name = "status", length = 100)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_money")
    private double totalMoney;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;
}
