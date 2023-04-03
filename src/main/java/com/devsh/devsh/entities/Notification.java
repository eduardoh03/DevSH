package com.devsh.devsh.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Timestamp createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Timestamp readAt;

    private Boolean read;

    @ManyToOne
    private Profile profile;

    public Notification() {
    }

    public Notification(Long id, String title, String message, Timestamp createdAt, Boolean read) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.read = read;
    }

    public Notification(Long id, String title, String message, Boolean read, Profile profile) {
        this.id = id;
        this.title = title;
        this.message = message;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        createdAt = Timestamp.valueOf(dateTime.atZoneSameInstant(ZoneId.of("Z")).toLocalDateTime());
    }

    @PreUpdate
    public void preUpdate() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        readAt = Timestamp.valueOf(dateTime.atZoneSameInstant(ZoneId.of("Z")).toLocalDateTime());
    }


    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
