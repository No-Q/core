package com.noq.api.model.response;

import com.noq.dependencies.db.model.enums.RestaurantType;

public class RestaurantResponse {

    private String name;
    private Integer costPerPerson;
    private String landmark;
    private Boolean vegOnly;
    private String company;
    private String type;
    private String email;
    private String phone;

    public RestaurantResponse(String name, Integer costPerPerson, String landmark,
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

    private void setType(RestaurantType restaurantType) {
        if (type != null)
            type = restaurantType.name();
        else
            type = "NA";
    }
}
