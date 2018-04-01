package com.noq.email.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    JavaMailSender mailSender;

    Gson gson = new Gson();

    @Override
    public void sendMail(String toAddress, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(toAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);

        LOGGER.info("Send config for user config verification:"+gson.toJson(email));

    }

}
