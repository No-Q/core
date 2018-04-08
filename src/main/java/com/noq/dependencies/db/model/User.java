package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.PaymentMethod;
import com.noq.dependencies.db.model.enums.UserRole;
import com.noq.dependencies.db.model.enums.UserSignupType;
import com.noq.dependencies.db.model.enums.UserStatus;

import javax.persistence.*;
import java.util.Set;

@Entity(name="user")
public class User extends BaseEntity{

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
    @Column(name = "phone", nullable = false)
    private String phone;
	@Column(name = "status")
	private UserStatus userStatus;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "userAge")
	private Integer userAge;
	@Column(name = "userSignupType")
	private UserSignupType userSignupType;
	@Column(name = "isEmailVerified")
	private Boolean isEmailVerified;
	@Column(name = "isPhoneVerified")
	private Boolean isPhoneVerified;
	@Column(name = "userTimezone")
	private String userTimezone;
	@Column(name = "defaultPaymentMethod")
	private PaymentMethod defaultPaymentMethod;
    @Column(name = "referral_code")
    private String referralCode;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private UserRole role;


    @OneToMany(targetEntity=Address.class, mappedBy="user",
            fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Address> addresses;

	public User() {
		super();
	}

    public User(String name, String email, String phone, String referralCode,
                Float rating, Set<Address> addresses) {
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
        this.addresses = addresses;
        this.name = name;
    }

    public User(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public UserSignupType getUserSignupType() {
        return userSignupType;
    }

    public void setUserSignupType(UserSignupType userSignupType) {
        this.userSignupType = userSignupType;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public String getUserTimezone() {
        return userTimezone;
    }

    public void setUserTimezone(String userTimezone) {
        this.userTimezone = userTimezone;
    }

    public PaymentMethod getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(PaymentMethod defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
                ", userStatus=" + userStatus +
                ", avatar='" + avatar + '\'' +
                ", userAge=" + userAge +
                ", userSignupType=" + userSignupType +
                ", isEmailVerified=" + isEmailVerified +
                ", isPhoneVerified=" + isPhoneVerified +
                ", userTimezone='" + userTimezone + '\'' +
                ", defaultPaymentMethod=" + defaultPaymentMethod +
                ", referralCode='" + referralCode + '\'' +
                ", rating=" + rating +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", addresses=" + addresses +
                ", id=" + id +
                ", active=" + active +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}