package com.noq.dependencies.db.model;

import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;
import javax.persistence.*;

@Table(name="item")
@Entity
public class Item extends BaseEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private ItemType itemType;
    @Column
    private MealType mealType;
    @Column
    private Float price;
    @Column
    private Boolean available;
    @Column
    private Integer preparationTime;
    @Column
    private Float discount;
    @Column
    private Boolean veg;
    @Column
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Item() {
    }

    public Item(String name, String description, ItemType itemType, MealType mealType, Float price, Boolean available,
                Integer preparationTime, Float discount, Boolean veg, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.mealType = mealType;
        this.price = price;
        this.available = available;
        this.preparationTime = preparationTime;
        this.discount = discount;
        this.veg = veg;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
