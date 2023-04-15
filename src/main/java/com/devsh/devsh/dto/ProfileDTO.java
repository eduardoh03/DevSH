package com.devsh.devsh.dto;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.entities.User;

import java.io.Serial;
import java.io.Serializable;

public class ProfileDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String imgUrl;
    private UserDTO user;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }
    public ProfileDTO(Profile entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.imgUrl = entity.getImgUrl();
    }

    public ProfileDTO(Long id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}