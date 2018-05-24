package com.noq.api.model.response;

import com.noq.dependencies.db.model.enums.RestaurantType;

import java.util.Date;

public class RestaurantListResponse {

    private Long id;
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

    public RestaurantListResponse(Long id,String name, Float costPerPerson, String landmark,
                                  Boolean vegOnly, String company, RestaurantType type,
                                  String email, String phone) {
        this.id = id;
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.company = company;
        setType(type);
        this.email = email;
        this.phone = phone;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setNextAvailable(NextAvailable nextAvailable) {
        this.nextAvailable = nextAvailable;
    }

    private void setType(RestaurantType restaurantType) {
        if (type != null)
            type = restaurantType.name();
        else
            type = "NA";
    }
}
