package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantDao extends CrudRepository<Restaurant, Long> {
}