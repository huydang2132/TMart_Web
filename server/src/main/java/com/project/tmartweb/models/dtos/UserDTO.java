package com.project.tmartweb.models.dtos;

import com.project.tmartweb.enums.RoleId;
import com.project.tmartweb.models.dtos.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO {
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
    private RoleId roleId;

    private Boolean deleted = Boolean.FALSE;
}
