package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AddressAddRequest {
    String line1;
    String line2;
    String city;
    @Valid
    @NotNull(message = "address state must not be null")
    String state;
    @Valid
    @NotNull(message = "address zip code must not be null")
    String zip;
    Double lat;
    Double lon;

    @JsonCreator
    public AddressAddRequest(@JsonProperty("line1") String line1,
                             @JsonProperty("line2") String line2,
                             @JsonProperty("city") String city,
                             @JsonProperty("state") String state,
                             @JsonProperty("zip") String zip,
                             @JsonProperty("lat") Double lat,
                             @JsonProperty("lon") Double lon) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "AddressAddRequest{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
