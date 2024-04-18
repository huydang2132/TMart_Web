package com.project.tmartweb.models.dtos;

import com.project.tmartweb.enums.OrderStatus;
import com.project.tmartweb.models.dtos.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
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

    private UUID userId;

    private String couponId;

    private List<OrderItem> orderItems;
}
