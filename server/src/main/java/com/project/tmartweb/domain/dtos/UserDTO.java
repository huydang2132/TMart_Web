package com.project.tmartweb.domain.dtos;

import com.project.tmartweb.domain.dtos.base.BaseDTO;
import com.project.tmartweb.domain.enums.RoleId;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String image;

    private String fullName;

    private String phoneNumber;

    private String email;

    private Date dateOfBirth;

    private String address;

    private RoleId roleId;

    private Boolean deleted = Boolean.FALSE;
}
