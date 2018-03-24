package com.noq.api.model;

import javax.persistence.*;

import java.util.Date;
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
	private UserAge userAge;
	@Column(name = "userSignupType")
	private UserSignupType userSignupType;
	@Column(name = "isEmailVerified")
	private boolean isEmailVerified;
	@Column(name = "isPhoneVerified")
	private boolean isPhoneVerified;
	@Column(name = "userTimezone", nullable = false)
	private String userTimezone;
	@Column(name = "defaultPaymentMethod", nullable = false)
	private DefaultPaymentMethod defaultPaymentMethod;
	
    @Column(name = "referral_code")
    String referralCode;
    @Column(name = "rating")
    float rating;
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

    public User(String name, String email, String phone, String referralCode, float rating, Set<Address> addresses) {
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
        this.addresses = addresses;
    }

    public User(String name, String email, String phone) {
        super();
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

    public float getRating() {
        return rating;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
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

	public UserAge getUserAge() {
		return userAge;
	}

	public void setUserAge(UserAge userAge) {
		this.userAge = userAge;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public UserSignupType getUserSignupType() {
		return userSignupType;
	}

	public void setUserSignupType(UserSignupType userSignupType) {
		this.userSignupType = userSignupType;
	}

	public boolean isEmailVerified() {
		return isEmailVerified;
	}

	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public boolean isPhoneVerified() {
		return isPhoneVerified;
	}

	public void setPhoneVerified(boolean isPhoneVerified) {
		this.isPhoneVerified = isPhoneVerified;
	}

	public String getUserTimezone() {
		return userTimezone;
	}

	public void setUserTimezone(String userTimezone) {
		this.userTimezone = userTimezone;
	}

	public DefaultPaymentMethod getDefaultPaymentMethod() {
		return defaultPaymentMethod;
	}

	public void setDefaultPaymentMethod(DefaultPaymentMethod defaultPaymentMethod) {
		this.defaultPaymentMethod = defaultPaymentMethod;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}