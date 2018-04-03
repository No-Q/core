package com.noq.db.dao;

import com.noq.db.model.User;
import com.noq.db.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenDao extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String verificationToken);
    VerificationToken findByUser(User user);
}
