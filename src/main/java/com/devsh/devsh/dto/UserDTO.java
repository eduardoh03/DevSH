package com.devsh.devsh.dto;

import com.devsh.devsh.entities.User;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {
    private Long id;
    @Email(message = "Favor entrar com um email válido")
    private String login;
    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
