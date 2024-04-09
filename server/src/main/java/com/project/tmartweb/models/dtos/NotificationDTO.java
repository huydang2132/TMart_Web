package com.project.tmartweb.models.dtos;

import com.project.tmartweb.models.dtos.base.BaseDTO;
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
    @NotBlank(message = "Nội dung thông báo không được để trống!")
    private String content;

    private Boolean read;

    private UUID userId;

    private UUID orderDetailId;
}
