package com.shobujghor.app.notification.service;

import com.shobujghor.app.notification.dynamo.EmailVerificationTokenRepository;
import com.shobujghor.app.utility.constants.NotificationType;
import com.shobujghor.app.utility.models.EmailVerificationToken;
import com.shobujghor.app.utility.request.notification.NotificationRequest;
import com.shobujghor.app.utility.request.notification.SendEmailRequest;
import com.shobujghor.app.utility.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${client.url}")
    private String clientBaseUrl;

    private final EmailService emailService;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    public void processNotification(NotificationRequest request) {
        if (NotificationType.EMAIL_VERIFICATION == request.getType()) {

            var token = createAndSaveEmailVerificationToken(request.getReceiverEmail());

            var sendEmailRequest = SendEmailRequest.builder()
                    .receiverEmail(request.getReceiverEmail())
                    .subject(EmailUtil.EMAIL_VERIFICATION_SUBJECT)
                    .body(EmailUtil.getEmailVerificationBody(getEmailVerificationLink(token)))
                    .build();

            var emailSent = emailService.sendEmail(sendEmailRequest);

            if (emailSent) {

            } else {
                throw new RuntimeException("Failed to send email");
            }
        }
    }

    private String createAndSaveEmailVerificationToken(String email) {
        var token = UUID.randomUUID().toString();

        var emailVerificationToken = EmailVerificationToken.builder()
                .email(email)
                .tokenExpiryDate(LocalDateTime.now().plusHours(24))
                .token(token)
                .build();

        emailVerificationTokenRepository.saveData(emailVerificationToken);

        return token;
    }

    private String getEmailVerificationLink(String token) {
         return clientBaseUrl + "/verify-email?token=" + token;
    }
}
