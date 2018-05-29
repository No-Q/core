package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantDao extends PagingAndSortingRepository<Restaurant, Long>, QuerydslPredicateExecutor<Restaurant> {

    Iterable<Restaurant> findByActive(Boolean active);

    String HOUR_AVAILABLE_QUERY =
            "from Restaurant as r inner join r.availability as ra " +
                    "where ra.hourOfOperationAvailable= :available and ra.hour= :hour and ra.dayOfWeek= :day";
    @Query(HOUR_AVAILABLE_QUERY)
    List<Object[]> findByDayAndHourAndHOAvailable(
            @Param("day") int day,@Param("hour") int hour, @Param("available") boolean HoAvailable);
    /**
     *  code for iterating over result from join query
     *      List<Object[]> listResult = restaurantDao.findByActive(Boolean.TRUE);
     *       for (Object[] row : listResult) {
     *          Restaurant restaurant = (Restaurant) row[0];
     *          ...
     */
}