package com.noq.api.model.request;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartAddRequest {

    private Long cartId;
    private Map<Long,Integer> itemToQuantityMap;
    private Long userId;
    private String specialInstructions;
    private Long offerCode;

    @JsonCreator
    public CartAddRequest(
            @JsonProperty("cartId") Long cartId,
            @JsonProperty("itemToQuantityMap") Map<Long, Integer> itemToQuantityMap,
            @JsonProperty("userId") Long userId,
            @JsonProperty("instructions") String specialInstructions,
            @JsonProperty("offerCode") Long offerCode) {

        this.cartId = cartId;
        this.itemToQuantityMap = itemToQuantityMap;
        this.userId = userId;
        this.specialInstructions = specialInstructions;
        this.offerCode = offerCode;
    }

    public Map<Long, Integer> getItemToQuantityMap() {
        return itemToQuantityMap;
    }

    public Long getUserId() {
        return userId;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public Long getOfferCode() {
        return offerCode;
    }

    public Long getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "CartAddRequest{" +
                "cartId=" + cartId +
                ", itemToQuantityMap=" + itemToQuantityMap +
                ", userId=" + userId +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", offerCode=" + offerCode +
                '}';
    }
}
