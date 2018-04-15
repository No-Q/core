package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.db.model.RestaurantAvailability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantAvailabilityDao extends CrudRepository<RestaurantAvailability,Long>{

    RestaurantAvailability findByRestaurantAndDayOfWeekAndHour(Restaurant restaurant,Integer day,Integer hour);

    String NEXT_HO_AVAILABLE_QUERY =
            "select min(hour) from RestaurantAvailability as ra where ra.restaurant.id = :restaurantId and " +
                    "ra.dayOfWeek = :dayOfWeek and hour >= :hourOfDay and ra.hourOfOperationAvailable = true";

    @Query(NEXT_HO_AVAILABLE_QUERY)
    Integer findNextHOAvailableHour(
            @Param("restaurantId") Long restaurantId,@Param("dayOfWeek") Integer dayOfWeek,@Param("hourOfDay") Integer hourOfDay);
}