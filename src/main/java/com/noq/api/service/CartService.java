package com.noq.api.service;

import com.google.gson.Gson;
import com.noq.api.model.request.CartAddRequest;
import com.noq.api.model.response.CartResponse;
import com.noq.dependencies.db.dao.CartDao;
import com.noq.dependencies.db.dao.CartToItemMappingDao;
import com.noq.dependencies.db.model.Cart;
import com.noq.dependencies.db.model.CartToItemMapping;
import com.noq.dependencies.db.model.Item;
import com.noq.dependencies.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartToItemMappingDao cartToItemMappingDao;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    private Gson gson = new Gson();

    public String getCartDetails(Long id) {
        CartResponse response = null;
        try {
            Cart cart = getCart(id);
            if (cart != null) {
                response = transformToResponse(cart);
            }else{
                throw new ValidationException("Cart with given id is not found");
            }
        }catch (Exception e){
            LOGGER.error("Exception while getting cart:",e);
        }
        return gson.toJson(response);
    }

    public Cart getCart(Long id){
        Cart cart = null;
        Optional<Cart> cartOptional = cartDao.findById(id);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        }
        return cart;
    }

    private CartResponse transformToResponse(Cart cart) {
        CartResponse response = new CartResponse(cart.getId(),cart.getTotalPrice(),
                cart.getTotalDiscount(),cart.getTotalCost());
        response.setItems(getCartToItemMapping(cart));

        response.setRestaurantName(cart.getRestaurant().getName());
        return response;
    }

    private Map<Long,Integer> getCartToItemMapping(Cart cart) {
        Map<Long,Integer> items = new HashMap<>();
        Iterable<CartToItemMapping> itemMappings = cartToItemMappingDao.findByCartId(cart.getId());
        for(CartToItemMapping mapping : itemMappings){
            Item item = itemService.getItem(mapping.getItemId());
            if(item != null) {
                items.put(item.getId(), mapping.getQuantity());
            }
        }
        return items;
    }

    public String addCart(CartAddRequest request) {
        CartResponse response = null;
        try {
            User user = userService.getUser(request.getUserId());
            Cart cart = new Cart(user);
            cartDao.save(cart);

            updateCart(cart,request);

            response = transformToResponse(cart);
        }catch (Exception e){
            LOGGER.error("Exception while adding cart:",e);
        }
        return gson.toJson(response);
    }

    private void updateCart(Cart cart, CartAddRequest request) {
        Item firstItem = null;
        Double totalCost = 0.0;
        for (Long itemId : request.getItemToQuantityMap().keySet()) {
            Item item = itemService.getItem(itemId);

            if (firstItem == null) {
                firstItem = item;
                cart.setRestaurant(firstItem.getRestaurant());
            }
            totalCost += item.getPrice();

            CartToItemMapping mapping = new CartToItemMapping(cart.getId(), itemId,
                    request.getItemToQuantityMap().get(itemId));
            mapping.setActive(Boolean.TRUE);
            cartToItemMappingDao.save(mapping);
        }
        cart.setActive(Boolean.TRUE);

        cart.setTotalCost(totalCost);

        cart.setOfferId(request.getOfferCode());
        Double discount = getDiscount(request.getOfferCode(), totalCost);
        cart.setTotalDiscount(discount);
        cart.setTotalOrderPrice(totalCost - discount);

        cart.setGst(getGST(totalCost));
        cart.setSpecialInstructions(request.getSpecialInstructions());

        cartDao.save(cart);
    }

    public String updateCart(CartAddRequest request) {
        CartResponse response = null;
        try {
            Cart cart = getCart(request.getCartId());
            if (cart != null) {
                response = transformToResponse(cart);
                updateCart(cart, request);

            }else {
                throw new ValidationException("Cart with given id is not found");
            }
            response = transformToResponse(cart);
        }catch (Exception e){
            LOGGER.error("Exception while updating cart:",e);
        }
        return gson.toJson(response);
    }

    private Double getDiscount(Long offerCode, Double totalCost) {
        return 2.0;
    }

    private Double getGST(Double totalCost) {
        return 1.0;
    }

}
