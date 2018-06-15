package com.noq.dependencies.db.model;

import javax.persistence.*;

@Table(name="cart")
@Entity
public class Cart extends BaseEntity {

    private Long offerId;
    private Double totalCost;
    private Double totalDiscount;
    private Double totalPrice;
    private Double gst;
    private Double totalOrderPrice;
    private String specialInstructions;
    private Double tips;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart(User user) {
        super();
        this.user = user;
    }

    public Cart() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Long getOfferId() {
        return offerId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getGst() {
        return gst;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public Double getTips() {
        return tips;
    }

    public User getUser() {
        return user;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public void setTips(Double tips) {
        this.tips = tips;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
