package com.noq.api.model.request;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

public class RestaurantSearchByLocationRequest {
	    @NotNull(message = "Latitude")
	    private double Latitude;
	    @NotNull(message = "Longitude")
	    private double Longitude;
	    @NotNull(message = "Radius in Km")
	    private int Radius;
	    @NotNull
        private DayOfWeek dayOfWeek;
	    @NotNull
        private int hourOfDay;

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    @Override
    public String toString() {
        return "RestaurantSearchByLocationRequest{" +
                "Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Radius=" + Radius +
                ", dayOfWeek=" + dayOfWeek +
                ", hourOfDay=" + hourOfDay +
                '}';
    }
}
