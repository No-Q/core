package com.noq.api.response;

public class AddressResponse {

    String line1;
    String line2;
    String city;
    String state;
    String zip;
    double lat;
    double lon;
    Long userId;

    public AddressResponse(String line1, String line2, String city, String state,
                           String zip, double lat, double lon, Long userId) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.userId = userId;
    }
}
