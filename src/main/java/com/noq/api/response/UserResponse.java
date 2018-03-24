package com.noq.api.response;

public class UserResponse {
    Long id;
    String name;
    String email;
    String phone;
    String referralCode;
    Integer rating;

    public UserResponse(Long id,String name, String email, String phone, String referralCode, Integer rating) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
    }
}
