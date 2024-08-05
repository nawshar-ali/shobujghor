package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.notification.ssm.SsmKeys;
import com.shobujghor.app.utility.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class CommonNotificationProcessorTask {

    @Qualifier(value = "securedApplicationProperties")
    private final Map<String, String> securedApplicationProperties;
    private final JavaMailSender mailSender;
    private final String sender;

    public CommonNotificationProcessorTask(Map<String, String> securedApplicationProperties) {
        this.securedApplicationProperties = securedApplicationProperties;
        this.sender = securedApplicationProperties.get(SsmKeys.EMAIL_USERNAME_KEY);
        this.mailSender = initJavaMail();
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

    protected void sendMail(EmailDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        try {
            log.info("Sending Email | SimpleMailMessage: {}", message);
            mailSender.send(message);
        } catch (Exception ex) {
            log.error("Failed to send email");
            throw new RuntimeException(ex);
        }
    }
}
