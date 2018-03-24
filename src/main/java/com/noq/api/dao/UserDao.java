package com.noq.api.dao;

import org.springframework.data.repository.CrudRepository;
import com.noq.api.model.User;


public interface UserDao extends CrudRepository<User, Long>{

	User findByName(String name);

    User findByEmail(String email);
}
