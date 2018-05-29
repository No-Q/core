package com.noq.api.model.request;

import java.time.DayOfWeek;

public class RestaurantSearchRequest {

    private Double Latitude;
    private Double Longitude;
    private Integer Radius;
    private DayOfWeek dayOfWeek;
    private Integer hourOfDay;
    private String name;
    private String cuisineType;

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public Integer getRadius() {
        return Radius;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    public String getName() {
        return name;
    }

    public String getCuisineType() {
        return cuisineType;
    }
}
