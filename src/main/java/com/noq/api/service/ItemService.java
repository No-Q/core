package com.noq.api.service;

import com.google.gson.Gson;
import com.noq.api.model.request.ItemAddRequest;
import com.noq.api.model.response.ItemResponse;
import com.noq.dependencies.db.dao.ItemDao;
import com.noq.dependencies.db.model.Item;
import com.noq.dependencies.db.model.QItem;
import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    ItemDao itemDao;
    @Autowired
    RestaurantService restaurantService;

    private final Gson gson = new Gson();

    public String getAll() {
        Iterable<Item> itemIterable = itemDao.findAll();

        return getItemListResponse(itemIterable);
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

        Restaurant restaurant = null;
        if(restaurantId != null)
            restaurant = restaurantService.get(restaurantId);
        MealType mealType = MealType.fromString(mealTypeStr);
        ItemType itemType = ItemType.fromString(itemTypeStr);
        Iterable<Item> itemIterable = getQuery(restaurant,mealType,itemType,itemName);

        return getItemListResponse(itemIterable);
    }

    private Iterable<Item> getQuery(Restaurant restaurant, MealType mealType, ItemType itemType, String itemName) {
        QItem item = QItem.item;

        Predicate predicate = item.active.eq(Boolean.TRUE);

        if(restaurant != null)
            predicate = item.restaurant.eq(restaurant).and(predicate);
        if(mealType != MealType.INVALID)
            predicate = item.mealType.eq(mealType).and(predicate);
        if(itemType != ItemType.INVALID)
            predicate = item.itemType.eq(itemType).and(predicate);
        if(itemName != null)
            predicate = item.name.contains(itemName).and(predicate);

        Iterable<Item> items = itemDao.findAll(predicate);
        return items;
    }


    private String getItemListResponse(Iterable<Item> itemIterable) {
        List<ItemResponse> responses = new ArrayList<>();

        for(Item item: itemIterable){
            ItemResponse response = new ItemResponse(item.getName(),item.getDescription(),
                    item.getPrice(),item.getDiscount(),item.getAvailable(),item.getPreparationTime(),
                    item.getImage(),item.getVeg());
            responses.add(response);
        }
        return gson.toJson(responses);
    }

}
