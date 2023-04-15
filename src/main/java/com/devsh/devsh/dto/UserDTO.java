package com.devsh.devsh.dto;

import com.devsh.devsh.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class UserDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
