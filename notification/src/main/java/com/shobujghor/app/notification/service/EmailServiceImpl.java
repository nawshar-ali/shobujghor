package com.shobujghor.app.notification.service;

import com.shobujghor.app.utility.request.notification.SendEmailRequest;
import com.shobujghor.app.utility.ssm.SsmKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Qualifier(value = "securedApplicationProperties")
    private final Map<String, String> securedApplicationProperties;
    private final JavaMailSender mailSender;
    private final String sender;

    public EmailServiceImpl(Map<String, String> securedApplicationProperties) {
        this.securedApplicationProperties = securedApplicationProperties;
        this.sender = securedApplicationProperties.get(SsmKeys.EMAIL_USERNAME_KEY);
        this.mailSender = initJavaMail();
    }

    @Override
    public boolean sendEmail(SendEmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(request.getReceiverEmail());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        log.info("Sending Email | SendEmailRequest: {} | SimpleMailMessage: {}", request, message);

        try {
            mailSender.send(message);
            return true;
        } catch (Exception ex) {
            log.error("Failed to send email", ex);
            return false;
        }
    }

    private JavaMailSender initJavaMail() {
        var javaMail = new JavaMailSenderImpl();
        javaMail.setHost("smtp.gmail.com");
        javaMail.setPort(587);
        Properties props = javaMail.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        javaMail.setUsername(securedApplicationProperties.get(SsmKeys.EMAIL_USERNAME_KEY));
        javaMail.setPassword(securedApplicationProperties.get(SsmKeys.EMAIL_PASSWORD_KEY));
        return javaMail;
    }
}
