package com.noq.dependencies.db.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="user")
public class User extends BaseEntity{

	@Column(name = "name", nullable = false)
	String name;
	@Column(name = "email", nullable = false)
	String email;
    @Column(name = "phone", nullable = false)
    String phone;
    @Column(name = "referral_code")
    String referralCode;
    @Column(name = "rating")
    Integer rating;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @OneToMany(targetEntity=Address.class, mappedBy="user",
            fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Address> addresses;

	public User() {
		super();
	}

    public User(String name, String email, String phone, String referralCode, Integer rating, Set<Address> addresses) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
        this.addresses = addresses;
    }

    public User(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", referralCode='" + referralCode + '\'' +
                ", rating=" + rating +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", addresses=" + addresses +
                ", id=" + id +
                ", active=" + active +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}