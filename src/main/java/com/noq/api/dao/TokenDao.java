package com.noq.api.dao;

import com.noq.api.model.User;
import com.noq.api.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenDao extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String verificationToken);
    VerificationToken findByUser(User user);
}
