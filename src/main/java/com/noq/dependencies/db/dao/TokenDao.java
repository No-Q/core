package com.noq.dependencies.db.dao;

import com.noq.dependencies.db.model.User;
import com.noq.dependencies.db.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenDao extends CrudRepository<VerificationToken, Long> {

    VerificationToken findByToken(String verificationToken);
    VerificationToken findByUser(User user);

    VerificationToken findByTokenAndType(String verificationToken, String type);
}
