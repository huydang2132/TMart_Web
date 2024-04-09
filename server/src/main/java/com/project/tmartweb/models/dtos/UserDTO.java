package com.project.tmartweb.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.tmartweb.models.dtos.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO {
    private UUID id;

    private String userName;

    private String password;

    private String image;

    private String fullName;

    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private String email;

    private Date dateOfBirth;

    private String address;

    @NotNull(message = "Role cannot be null")
    private String roleId;

    private Boolean deleted = Boolean.FALSE;
}
