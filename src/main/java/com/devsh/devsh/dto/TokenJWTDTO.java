package com.devsh.devsh.dto;

import java.io.Serial;
import java.io.Serializable;

public class TokenJWTDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String type = "Bearer ";
    private String token;
    private Long user_id;

    public TokenJWTDTO(String token, Long user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public Long getUser_id() {
        return user_id;
    }
}