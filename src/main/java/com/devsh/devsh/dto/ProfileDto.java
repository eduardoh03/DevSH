package com.devsh.devsh.dto;

import com.devsh.devsh.entities.Profile;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProfileDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull @NotEmpty(message = "Password cannot be empty")
    private String password;
    @Email @NotNull @NotEmpty(message = "Email cannot be empty")
    private String email;

    public ProfileDto() {
    }

    public ProfileDto(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.password = profile.getPassword();
        this.email = profile.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}