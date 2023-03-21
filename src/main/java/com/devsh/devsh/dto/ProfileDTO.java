package com.devsh.devsh.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProfileDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @Email
    @NotNull
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String imgUrl;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String name, String password, String email, String imgUrl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}