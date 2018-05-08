package com.noq.api.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;

public class ItemAddRequest {

    Long restaurantId;
    String name;
    String description;
    Boolean veg;
    Float price;
    ItemType itemType;
    MealType mealType;
    Integer preparationTime;
    Float discount;

    @JsonCreator
    public ItemAddRequest(
            @JsonProperty("restaurantId") Long restaurantId,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("veg") Boolean veg,
            @JsonProperty("price") Float price,
            @JsonProperty("itemType") ItemType itemType,
            @JsonProperty("mealType") MealType mealType,
            @JsonProperty("preparationTime") Integer preparationTime,
            @JsonProperty("discount") Float discount) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.veg = veg;
        this.price = price;
        this.itemType = itemType;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.discount = discount;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getVeg() {
        return veg;
    }

    public Float getPrice() {
        return price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public MealType getMealType() {
        return mealType;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public Float getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "ItemAddRequest{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", veg=" + veg +
                ", price=" + price +
                ", itemType=" + itemType +
                ", mealType=" + mealType +
                ", preparationTime=" + preparationTime +
                ", discount=" + discount +
                '}';
    }
}
