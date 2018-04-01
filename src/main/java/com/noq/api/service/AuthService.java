package com.noq.api.service;

import com.noq.api.dto.UserDto;
import com.noq.api.event.publisher.EventPublisher;
import com.noq.api.model.User;
import com.noq.api.model.VerificationToken;
import com.noq.event.model.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Calendar;
import java.util.Locale;

@Service
public class AuthService {

    @Autowired
    UserService userService;
    @Autowired
    EventPublisher eventPublisher;

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
                throw  new Exception("Exception while generating event for sending verification config:",e);
            }
        }
    }

    public void confirmEmail(WebRequest request, String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "Invalid verification token";//messages.getMessage("auth.message.invalidToken", null, locale);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = "Verification token expired";//messages.getMessage("auth.message.expired", null, locale)
        }

        user.setActive(Boolean.TRUE);
        userService.update(user);
    }
}
