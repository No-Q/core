package com.noq.dependencies.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="cart_items")
@Entity
public class CartToItemMapping extends BaseEntity{

    private Long cartId;
    private Long itemId;
    private Integer quantity;

    public CartToItemMapping() {
    }

    public CartToItemMapping(Long cartId, Long itemId, Integer quantity) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
