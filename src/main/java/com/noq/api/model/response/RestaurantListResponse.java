package com.noq.api.model.response;

public class RestaurantListResponse {

    private Long id;
    private String name;
    private Float costPerPerson;
    private String landmark;
    private Boolean vegOnly;
    private String company;
    private String email;
    private String phone;
    private Boolean available;
    private NextAvailable nextAvailable;
    private Double distance;
    private String imageUrl;
    private Integer avgPreparationTime;
    private String cuisineType;

    public RestaurantListResponse(Long id, String name, Float costPerPerson, String landmark,
                                  Boolean vegOnly, String company, String email, String phone,
                                  String imageUrl, Integer avgPreparationTime,String cuisineType) {
        this.id = id;
        this.name = name;
        this.costPerPerson = costPerPerson;
        this.landmark = landmark;
        this.vegOnly = vegOnly;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.avgPreparationTime = avgPreparationTime;
        this.cuisineType = cuisineType;
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

}
