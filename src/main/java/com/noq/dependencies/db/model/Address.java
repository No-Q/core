package com.noq.dependencies.db.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.noq.api.model.request.AddressAddRequest;
import com.noq.dependencies.search.QuadKeyUtil;

@Entity(name="address")
public class Address extends BaseEntity{

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;
    private Double lat;
    private Double lon;
    private String quadKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_d")
    Restaurant restaurant;

    public Address() {
        super();
    }

    public Address(String line1, String line2, String city, String state,
                   String zip, double lat, double lon, User user) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.user = user;
        // we can move zoomlevel to config ç
        this.quadKey = QuadKeyUtil.LatLongToQuadKey(16, lat, lon);
        this.restaurant = restaurant;
    }

    public Address(AddressAddRequest address,Restaurant restaurant) {
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
        this.city = address.getCity();
        this.state = address.getState();
        this.zip = address.getZip();
        this.lat = address.getLat();
        this.lon = address.getLon();
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuadKey() {
		return quadKey;
	}
    
    public void setQuadKey(String quadKey) {
		this.quadKey = quadKey;
	}

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", quadKey='" + quadKey + '\'' +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", id=" + id +
                ", active=" + active +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
