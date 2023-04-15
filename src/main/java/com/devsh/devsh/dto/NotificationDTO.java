package com.devsh.devsh.dto;
import com.devsh.devsh.entities.Notification;
import org.aspectj.weaver.ast.Not;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class NotificationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String message;
    private Instant createdAt;
    private Boolean read;
    private ProfileDTO profile;

    public NotificationDTO() {
    }

    public NotificationDTO(Long id, String title, String message, Instant createdAt, Boolean read) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.read = read;
    }

    public NotificationDTO(Long id, String title, String message, Instant createdAt, Boolean read, ProfileDTO profile) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.read = read;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
