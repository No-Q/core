package com.noq.api.model.response;

import com.noq.dependencies.db.model.Item;
import java.util.Map;

public class CartResponse {

    private Long cartId;
    private String restaurantName;
    private Map<Long,Integer> Items;
    private Double totalPrice;
    private Double totalDiscount;
    private Double priceToPay;

    public CartResponse(Long cartId, Double totalPrice,
                        Double totalDiscount, Double priceToPay) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.priceToPay = priceToPay;
    }

    public void setItems(Map<Long, Integer> items) {
        Items = items;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
