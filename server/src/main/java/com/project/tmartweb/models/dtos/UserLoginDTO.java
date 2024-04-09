package com.project.tmartweb.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    private String userName;

    private String password;

    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private String email;
}
