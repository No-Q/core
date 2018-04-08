package com.noq.dependencies.db.dao;

import org.springframework.data.repository.CrudRepository;
import com.noq.dependencies.db.model.User;

public interface UserDao extends CrudRepository<User, Long>{

	User findByName(String name);

    User findByEmail(String email);
    
    User findByPhone(String phone);
}
