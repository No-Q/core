package com.noq.dependencies.db.model;

import javax.persistence.*;

@Table(name="restaurant_availability")
@Entity
public class RestaurantAvailability extends BaseEntity {

    @Column(name="ho_available")
    private Boolean hourOfOperationAvailable;
    @Column(name="partner_available")
    private Boolean partnerAvailable;
    @Column(name="hour")
    private Integer hour;
    @Column
    private Integer dayOfWeek;
    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    public RestaurantAvailability() {
        super();
    }

    public RestaurantAvailability(Restaurant restaurant, Integer hour,Boolean hourOfOperationAvailable,
                                  Boolean partnerAvailable) {
        this.hourOfOperationAvailable = hourOfOperationAvailable;
        this.partnerAvailable = partnerAvailable;
        this.hour = hour;
        this.restaurant = restaurant;
    }

    public RestaurantAvailability(Restaurant restaurant,Integer dayOfWeek,Integer hour,Boolean hourOfOperationAvailable ) {
        this.hourOfOperationAvailable = hourOfOperationAvailable;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.restaurant = restaurant;
        this.partnerAvailable = Boolean.TRUE;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Boolean getPartnerAvailable() {
        return partnerAvailable;
    }

    public void setPartnerAvailable(Boolean partnerAvailable) {
        this.partnerAvailable = partnerAvailable;
    }

    public Boolean getHourOfOperationAvailable() {
        return hourOfOperationAvailable;
    }

    public void setHourOfOperationAvailable(Boolean hourOfOperationAvailable) {
        this.hourOfOperationAvailable = hourOfOperationAvailable;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "RestaurantAvailability{" +
                ", hourOfOperationAvailable=" + hourOfOperationAvailable +
                ", partnerAvailable=" + partnerAvailable +
                ", restaurant=" + restaurant +
                '}';
    }
}
