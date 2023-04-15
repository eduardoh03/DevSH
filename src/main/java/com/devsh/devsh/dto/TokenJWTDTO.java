package com.devsh.devsh.dto;

import java.io.Serial;
import java.io.Serializable;

public class TokenJWTDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String type = "Bearer ";
    private String token;
    private Long profile_id;

    public TokenJWTDTO(String token, Long profile_id) {
        this.token = token;
        this.profile_id = profile_id;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public Long getProfile_id() {
        return profile_id;
    }
}