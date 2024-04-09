package com.project.tmartweb.models.entities;

import com.project.tmartweb.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "image", length = 100)
    private String image;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
