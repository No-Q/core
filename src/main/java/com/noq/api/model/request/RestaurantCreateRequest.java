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
    @NotNull(message = "Restaurant cost per person must not be null")
    private Float costPerPerson;
    @NotNull(message = "Restaurant image url must not be null")
    private String imageUrl;
    @NotNull(message = "Restaurant avg meal preparation time must not be null")
    private Integer avgPreparationTime;
    @NotNull(message = "Restaurant cusine type must not be null")
    private String cuisineType;

    private AddressAddRequest address;

    @JsonCreator
    public RestaurantCreateRequest(@JsonProperty("name") String name,
                             @JsonProperty("email") String email,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("vegOnly") Boolean vegOnly,
                             @JsonProperty("address") AddressAddRequest address,
                             @JsonProperty("costPerPerson") Float costPerPerson,
                             @JsonProperty("imageUrl") String imageUrl,
                             @JsonProperty("avgPreparationTime") Integer avgPreparationTime,
                             @JsonProperty("cuisineType") String cuisineType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.vegOnly = vegOnly;
        this.address = address;
        this.costPerPerson = costPerPerson;
        this.imageUrl = imageUrl;
        this.avgPreparationTime = avgPreparationTime;
        this.cuisineType = cuisineType;
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
    public AddressAddRequest getAddress() {
        return address;
    }

    public Float getCostPerPerson() {
        return costPerPerson;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getAvgPreparationTime() {
        return avgPreparationTime;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    @Override
    public String toString() {
        return "RestaurantCreateRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", vegOnly=" + vegOnly +
                ", phone='" + phone + '\'' +
                ", costPerPerson=" + costPerPerson +
                ", imageUrl='" + imageUrl + '\'' +
                ", avgPreparationTime=" + avgPreparationTime +
                ", cuisineType='" + cuisineType + '\'' +
                ", address=" + address +
                '}';
    }
}
