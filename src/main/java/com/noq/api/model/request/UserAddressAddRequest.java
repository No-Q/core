package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserAddressAddRequest {
    String line1;
    String line2;
    String city;
    @Valid
    @NotNull(message = "address state must not be null")
    String state;
    @Valid
    @NotNull(message = "address zip code must not be null")
    String zip;
    double lat;
    double lon;

    public UserAddressAddRequest(@JsonProperty("line1") String line1,
                                 @JsonProperty("line2") String line2,
                                 @JsonProperty("city") String city,
                                 @JsonProperty("state") String state,
                                 @JsonProperty("zip") String zip,
                                 @JsonProperty("lat") double lat,
                                 @JsonProperty("lon") double lon) {
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

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
