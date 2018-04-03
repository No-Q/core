package com.noq.db.dao;

import org.springframework.data.repository.CrudRepository;
import com.noq.db.model.User;


public interface UserDao extends CrudRepository<User, Long>{

	User findByName(String name);

    User findByEmail(String email);
    
    User findByPhone(String phone);
}
