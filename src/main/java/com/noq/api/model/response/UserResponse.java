package com.noq.api.model.response;

public class UserResponse {
    Long id;
    String name;
    String email;
    String phone;
    String referralCode;
    Float rating;

    public UserResponse(Long id,String name, String email,
                        String phone, String referralCode, Float rating) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.referralCode = referralCode;
        this.rating = rating;
    }
}
