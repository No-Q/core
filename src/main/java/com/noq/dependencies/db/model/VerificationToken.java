package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.VerificationTokenType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity(name="verification_token")
public class VerificationToken extends BaseEntity {
    private static final int EXPIRATION = 60 * 24;

    @Column
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column
    private Date expiryDate;

    @Column
    private String type;

    public VerificationToken() {
        super();
    }

    public VerificationToken(String token, User user, VerificationTokenType type) {
        this.token = token;
        this.user = user;
        this.type = type.name();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        this.active = Boolean.TRUE;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public String getToken() {
        return token;
    }

    public VerificationTokenType getType() {
        return VerificationTokenType.valueOf(type);
    }

    public void setType(VerificationTokenType type) {
        this.type = type.name();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}