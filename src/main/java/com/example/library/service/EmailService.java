package com.example.library.service;

import com.example.library.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation");
        try {
            SimpleMailMessage simpleMailMessage = createMailMessage(mail);

            javaMailSender.send(createMailMessage(mail));

            LOGGER.info("Mail sent");
        } catch (MailException e) {
            LOGGER.info("Failed to sent mail");
        }
    }
    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setText(mail.getMessage());
        mailMessage.setSubject(mail.getSubject());
        return mailMessage;
    }
}

