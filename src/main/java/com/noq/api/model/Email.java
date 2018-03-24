package com.noq.api.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Email extends BaseEntity {

    String email;
    boolean verified;
    EmailType type;

    @ManyToOne
    @JoinColumn(name = "user_d")
    User user;

    public Email(String email, boolean verified, EmailType type, User user) {
        this.email = email;
        this.verified = verified;
        this.type = type;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public EmailType getType() {
        return type;
    }

    public void setType(EmailType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                ", verified=" + verified +
                ", type=" + type +
                ", id=" + id +
                ", active=" + active +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
