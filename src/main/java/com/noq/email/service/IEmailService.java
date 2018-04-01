package com.noq.email.service;

public interface IEmailService {
    public void sendMail(String toAddress, String subject, String message);
}
