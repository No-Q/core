package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Item;
import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.db.model.enums.ItemType;
import com.noq.dependencies.db.model.enums.MealType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemDao extends CrudRepository<Item,Long>, QuerydslPredicateExecutor<Item>{

    @Query("from Item i where i.name like '%:name%'")
    List<Item> findByName(@Param("name") String name);
    List<Item> findByRestaurant(Restaurant restaurant);
    List<Item> findByMealType(MealType type);

  //  @Query("from Item i where i.restaurant.id == :restaurant.id and i.mealType == :mealType and i.itemType == :itemType and i.name like '%:name%'")
    Iterable<Item> findByRestaurantAndMealTypeAndItemTypeAndName(Restaurant restaurant, MealType mealType, ItemType itemType, String itemName);

}
