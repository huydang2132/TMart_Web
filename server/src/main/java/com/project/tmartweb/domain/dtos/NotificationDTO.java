package com.project.tmartweb.domain.dtos;

import com.project.tmartweb.domain.dtos.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDTO extends BaseDTO {
    private String title;

    @NotBlank(message = "Nội dung thông báo không được để trống!")
    private String content;

    private Boolean read = Boolean.FALSE;

    private UUID userId;

    private UUID orderId;
}
