package com.project.tmartweb.models.dtos;

import com.project.tmartweb.enums.OrderStatus;
import com.project.tmartweb.models.dtos.base.BaseDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.models.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends BaseDTO {
    @NotBlank(message = "Không được bỏ trống họ và tên!")
    private String fullName;

    @NotBlank(message = "Không đươc bỏ trống số điện thoại!")
    private String phoneNumber;

    private String address;

    private String note;

    private Timestamp orderDate;

    private OrderStatus status;

    @Min(0)
    private double totalMoney;

    private UUID userId;

    private String couponId;
}
