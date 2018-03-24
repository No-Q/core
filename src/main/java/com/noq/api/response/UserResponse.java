package com.noq.api.response;

public class UserResponse {
    Long id;
    String name;
    String email;
    String phone;
    String referralCode;
    float rating;

    public UserResponse(Long id,String name, String email, String phone, String referralCode, float rating) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
    }
}
