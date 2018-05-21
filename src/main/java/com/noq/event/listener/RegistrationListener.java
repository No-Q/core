package com.noq.event.listener;

import com.google.gson.Gson;
import com.noq.dependencies.db.dao.TokenDao;
import com.noq.dependencies.db.model.User;
import com.noq.api.service.UserService;
import com.noq.dependencies.db.model.VerificationToken;
import com.noq.dependencies.db.model.enums.VerificationTokenType;
import com.noq.dependencies.email.service.IEmailService;
import com.noq.event.model.OnRegistrationCompleteEvent;
import com.noq.dependencies.sms.ISmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
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

    @Autowired
    private ISmsService smsService;

    @Autowired
    TokenDao tokenDao;

    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        LOGGER.info("Received event:"+gson.toJson(event));
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {

        sendVerificationEmail(event);
        sendVerificationSms(event);
    }

    private void sendVerificationSms(OnRegistrationCompleteEvent event) {
        try {
            User user = event.getUser();
            String smsToken = ""+generateOtp();
            createVerificationToken(user,smsToken,VerificationTokenType.SMS);
            String smsText = "NoQ verification OTP:"+smsToken;
            smsService.sendSms(user.getPhone(),smsText);
        } catch (Exception e) {
            LOGGER.error("Exception while sending verification SMS: ",e);
        }
    }

    private BigInteger generateOtp() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(20, random);
    }

    private void sendVerificationEmail(OnRegistrationCompleteEvent event) {
        try {
            User user = event.getUser();
            String emailToken = UUID.randomUUID().toString();
            createVerificationToken(user,emailToken,VerificationTokenType.EMAIL);

            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    = event.getAppUrl() + "/email/verify?token=" + emailToken;
            String message = messages.getMessage("message.regSucc", null, event.getLocale());
            message = message + " " + "http://localhost:8080" + confirmationUrl;

            emailService.sendMail(recipientAddress,subject,message);
        } catch (Exception e) {
           LOGGER.error("Exception while sending verification email: ",e);
        }
    }

    private void createVerificationToken(User user, String token, VerificationTokenType type) {
        VerificationToken myToken = new VerificationToken(token, user, type);
        tokenDao.save(myToken);
    }
}