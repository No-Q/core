package com.noq.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements ISmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Override
    public void sendSms(String phone, String msg) {
        LOGGER.info("Sending sms:"+msg+" to:"+phone);
    }
}
