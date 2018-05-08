package com.noq.api.validator.item;

import com.noq.api.service.RestaurantService;
import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;

@Service
public class ItemFilterValidator {
    @Autowired
    RestaurantService restaurantService;

    public boolean isValid(Long restaurantId, String mealTypeStr, String itemTypeStr) throws ValidationException {
        Boolean valid = Boolean.TRUE;
        if(mealTypeStr != null) {
            MealType mealType = MealType.fromString(mealTypeStr);
            if(mealType == MealType.INVALID)
                valid = Boolean.FALSE;
        }
        if(itemTypeStr != null){
            ItemType itemType = ItemType.fromString(itemTypeStr);
            if(itemType == ItemType.INVALID){
                valid = Boolean.FALSE;
            }
        }
        Restaurant restaurant = restaurantService.get(restaurantId);
        if(restaurant == null){
            valid = Boolean.FALSE;
        }
        return valid;
    }
}
