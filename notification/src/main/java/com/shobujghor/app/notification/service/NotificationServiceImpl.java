package com.shobujghor.app.notification.service;

import com.shobujghor.app.utility.constants.NotificationType;
import com.shobujghor.app.utility.request.notification.NotificationRequest;
import com.shobujghor.app.utility.request.notification.SendEmailRequest;
import com.shobujghor.app.utility.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${client.url}")
    private String clientBaseUrl;

    private final EmailService emailService;

    @Override
    public void processNotification(NotificationRequest request) {
        if (NotificationType.EMAIL_VERIFICATION == request.getType()) {

            var sendEmailRequest = SendEmailRequest.builder()
                    .receiverEmail(request.getReceiverEmail())
                    .subject(EmailUtil.EMAIL_VERIFICATION_SUBJECT)
                    .body(EmailUtil.getEmailVerificationBody(getEmailVerificationLink(request.getEmailVerificationToken())))
                    .build();

            var emailSent = emailService.sendEmail(sendEmailRequest);

            if (!emailSent) {
                throw new RuntimeException("Failed to send email");
            }
        }
    }

    private String getEmailVerificationLink(String token) {
         return clientBaseUrl + "/verify-email?token=" + token;
    }
}
