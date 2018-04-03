package com.noq.event.listener;

import com.google.gson.Gson;
import com.noq.db.model.User;
import com.noq.api.service.UserService;
import com.noq.email.service.IEmailService;
import com.noq.event.model.OnRegistrationCompleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private IEmailService emailService;

    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        LOGGER.info("Received event:"+gson.toJson(event));
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/config/confirm.html?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        message = message + " rn" + "http://localhost:8080" + confirmationUrl;

        emailService.sendMail(recipientAddress,subject,message);
    }
}