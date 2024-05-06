package com.project.tmartweb.application.services.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String email) throws MessagingException {

    }

    @Override
    public void sendGreeting(String subject, String content, String email, String name, Boolean isHtmlFormat) throws MessagingException {

    }
}
