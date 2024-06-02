package com.project.tmartweb.application.services.email;

import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendEmail(String to, String subject, String email) throws MessagingException;

    void sendGreeting(
            String subject, String content, String email,
            String name, Boolean isHtmlFormat
    ) throws MessagingException;
}
