package com.project.tmartweb.domain.dtos;

import com.project.tmartweb.domain.dtos.base.BaseDTO;
import com.project.tmartweb.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private OrderStatus status;

    private UUID userId;

    private String couponId;

    private List<CartDTO> cartItems;
}
