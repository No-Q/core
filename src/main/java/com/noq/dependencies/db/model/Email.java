package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.EmailType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="email")
public class Email extends BaseEntity {

    private String email;
    private Boolean verified;
    private EmailType type;

    @ManyToOne
    @JoinColumn(name = "user_d")
    User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_d")
    Restaurant restaurant;

    public Email(String email, Boolean verified, EmailType type, User user) {
        this.email = email;
        this.verified = verified;
        this.type = type;
        this.user = user;
    }

    public Email(String email, Boolean verified, EmailType type, Restaurant restaurant) {
        this.email = email;
        this.verified = verified;
        this.type = type;
        this.restaurant = restaurant;
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
                "config='" + email + '\'' +
                ", verified=" + verified +
                ", type=" + type +
                ", id=" + id +
                ", active=" + active +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
