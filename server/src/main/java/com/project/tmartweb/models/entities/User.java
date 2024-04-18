package com.project.tmartweb.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tmartweb.models.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Column(name = "user_name", unique = true)
    private String userName;

    @JsonIgnore
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

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonIgnore
    @OneToOne
    @Transient
    private Token token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.getRole().getId().toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
