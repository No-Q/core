package com.noq.api.model.response;

import com.noq.dependencies.db.model.enums.RestaurantType;

import java.util.Date;

public class RestaurantListResponse {

    private String name;
    private Float costPerPerson;
    private String landmark;
    private Boolean vegOnly;
    private String company;
    private String type;
    private String email;
    private String phone;
    private Boolean available;
    private NextAvailable nextAvailable;
    private Double distance;

    public RestaurantListResponse(String name, Float costPerPerson, String landmark,
                                  Boolean vegOnly, String company, RestaurantType type,
                                  String email, String phone) {
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.company = company;
        setType(type);
        this.email = email;
        this.phone = phone;

    }

    public RestaurantListResponse(
            String name, Float costPerPerson, String landmark, Boolean vegOnly,
            String company, RestaurantType type, Boolean available,
            NextAvailable nextAvailable) {
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.company = company;
        setType(type);
        this.available = available;
        this.nextAvailable = nextAvailable;
    }

    public RestaurantListResponse(
            String name, Float costPerPerson, String landmark, Boolean vegOnly, String company,
            RestaurantType type, String email, String phone, double distance) {
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.company = company;
        setType(type);
        this.email = email;
        this.phone = phone;
        this.distance = distance;
    }

    private void setType(RestaurantType restaurantType) {
        if (type != null)
            type = restaurantType.name();
        else
            type = "NA";
    }
}
