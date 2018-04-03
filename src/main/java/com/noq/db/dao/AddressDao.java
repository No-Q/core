package com.noq.db.dao;

import com.noq.db.model.Address;
import com.noq.db.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {

    List<Address> findByUser(User user);
}
