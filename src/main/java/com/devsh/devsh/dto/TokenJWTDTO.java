package com.devsh.devsh.dto;

public class TokenJWTDTO {
    private String type;
    private String token;
    private Long user_id;

    public TokenJWTDTO(String type, String token, Long user_id) {
        this.type = type;
        this.token = token;
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}