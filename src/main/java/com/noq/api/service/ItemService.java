package com.noq.api.service;

import com.google.gson.Gson;
import com.noq.api.model.request.ItemAddRequest;
import com.noq.api.model.response.ItemResponse;
import com.noq.dependencies.db.dao.ItemDao;
import com.noq.dependencies.db.model.Item;
import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    ItemDao itemDao;
    @Autowired
    RestaurantService restaurantService;

    private final Gson gson = new Gson();

    public String getAll() {
        List<ItemResponse> responses = new ArrayList<>();
        Iterable<Item> itemIterable = itemDao.findAll();
        List<Item> items = new ArrayList<>();
        for(Item item: itemIterable){
            ItemResponse response = new ItemResponse(item.getName(),item.getDescription(),
                    item.getPrice(),item.getDiscount(),item.getAvailable(),item.getPreparationTime(),
                    item.getImage(),item.getVeg());
            responses.add(response);
        }
        return gson.toJson(responses);
    }

    public void save(ItemAddRequest request) throws Exception {
        Boolean available = Boolean.TRUE;
        Restaurant restaurant = restaurantService.get(request.getRestaurantId());;
        if (restaurant != null) {
            Item item = new Item(
                    request.getName(), request.getDescription(), request.getItemType(),
                    request.getMealType(), request.getPrice(), available,
                    request.getPreparationTime(), request.getDiscount(), request.getVeg(), restaurant);
            itemDao.save(item);
        }
    }

    public String getItems(Long restaurantId, String mealTypeStr, String itemName, String itemTypeStr) throws ValidationException {
        List<Item> items = new ArrayList<>();
        Restaurant restaurant =
                restaurantService.get(restaurantId);
        MealType mealType = MealType.fromString(mealTypeStr);
        ItemType itemType = ItemType.fromString(itemTypeStr);
        Iterable<Item> itemIterable = itemDao.findByRestaurantAndMealTypeAndItemTypeAndName(restaurant,mealType,itemType,itemName);
        for(Item item: itemIterable){
            items.add(item);
        }
        return gson.toJson(items);
    }
}
