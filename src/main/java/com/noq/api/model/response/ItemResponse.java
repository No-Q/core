package com.noq.api.model.response;

public class ItemResponse {
    String name;
    String description; Float price;
    Float discount;
    Boolean available; Integer preparationTime;
    byte[] image;
    Boolean veg;

    public ItemResponse(String name, String description, Float price, Float discount,
                        Boolean available, Integer preparationTime, byte[] image, Boolean veg) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.available = available;
        this.preparationTime = preparationTime;
        this.image = image;
        this.veg = veg;
    }
}
