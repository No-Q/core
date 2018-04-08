package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class RestaurantCreateRequest {

    @NotNull(message = "Restaurant name must not be null")
    private String name;
    @NotNull(message = "Restaurant email must not be null")
    private String email;
    @NotNull(message = "Restaurant veg only option must not be null")
    private Boolean vegOnly;
    @NotNull(message = "Restaurant phone must not be null")
    private String phone;

    @JsonCreator
    public RestaurantCreateRequest(@JsonProperty("name") String name,
                             @JsonProperty("email") String email,
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

    public Boolean getVegOnly() {
        return vegOnly;
    }

    public String getPhone() {
        return phone;
    }
}
