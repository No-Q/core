package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.Address;
import com.noq.dependencies.db.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {

    List<Address> findByUser(User user);
}
