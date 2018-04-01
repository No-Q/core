package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class UserCreateRequest {

    @Valid
    @NotNull(message = "user name must not be null")
    String name;
    @Valid
    @NotNull(message = "user config must not be null")
    String email;
    @Valid
    @NotNull(message = "user phone must not be null")
    String phone;

    @JsonCreator
    public UserCreateRequest(@JsonProperty("name") String name,
                             @JsonProperty("config") String email,
                             @JsonProperty("phone") String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "UserCreateRequest{" +
                "name='" + name + '\'' +
                ", config='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
