package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDao extends CrudRepository<Cart,Long> {

}
