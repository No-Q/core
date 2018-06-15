package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.CartToItemMapping;
import org.springframework.data.repository.CrudRepository;

public interface CartToItemMappingDao extends CrudRepository<CartToItemMapping,Long> {

    Iterable<CartToItemMapping> findByCartId(long id);
}
