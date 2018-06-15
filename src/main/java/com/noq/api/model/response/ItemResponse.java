package com.noq.api.model.response;

public class ItemResponse {
    Long id;
    String name;
    String description; Float price;
    Float discount;
    Boolean available; Integer preparationTime;
    byte[] image;
    Boolean veg;

    public ItemResponse(Long id, String name, String description, Float price, Float discount,
                        Boolean available, Integer preparationTime, byte[] image, Boolean veg) {
        this.id = id;
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
