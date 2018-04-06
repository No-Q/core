package com.noq.api.service;

import com.noq.api.model.request.UserDto;
import com.noq.api.event.publisher.EventPublisher;
import com.noq.dependencies.db.dao.TokenDao;
import com.noq.dependencies.db.model.User;
import com.noq.dependencies.db.model.VerificationToken;
import com.noq.dependencies.db.model.enums.VerificationTokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Calendar;

@Service
public class AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    UserService userService;
    @Autowired
    EventPublisher eventPublisher;
    @Autowired
    TokenDao tokenDao;

    public void registerUser(UserDto userDto, WebRequest request) throws Exception{

        if (userService.getUserByPhone(userDto.getPhone())!=null) {
            throw new ValidationException("There is already an account with phone number: "
                    +  userDto.getPhone());
        }else{
            User registeredUser = userService.register(userDto);
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(registeredUser, request.getLocale(), appUrl);
            } catch (Exception e) {
                throw  new Exception("Exception while generating event for sending verification token:",e);
            }
        }
    }

    public void verifyToken(WebRequest request, String token, VerificationTokenType type) {

        Calendar cal = Calendar.getInstance();
        String errorMsg = null;
        VerificationToken verificationToken = getVerificationToken(token,type);
        if (verificationToken == null) {
            //messages.getMessage("auth.message.invalidToken", null, locale);
            errorMsg = "Invalid verification token:"+token+" type:"+type.name();
        }else if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            //messages.getMessage("auth.message.expired", null, locale)
            errorMsg = "Verification token expired, token:"+token+" type:"+type;
        }
        if(errorMsg == null) {
            User user = verificationToken.getUser();
            user.setActive(Boolean.TRUE);
            userService.update(user);

            verificationToken.setActive(Boolean.FALSE);
            tokenDao.save(verificationToken);
        }else{
            LOGGER.error("Token verification failed. Error:"+errorMsg);
        }
    }

    public VerificationToken getVerificationToken(String VerificationToken, VerificationTokenType type) {
        return tokenDao.findByTokenAndType(VerificationToken,type.name());
    }

}
