package com.noq.api.dao;

import com.noq.api.model.Address;
import com.noq.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AddressDao extends CrudRepository<Address, Long> {

    List<Address> findByUser(User user);
}
