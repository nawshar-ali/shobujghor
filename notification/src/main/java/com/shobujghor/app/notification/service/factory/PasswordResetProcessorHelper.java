package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.EmailDto;
import com.shobujghor.app.utility.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("PasswordResetProcessorHelper")
public class PasswordResetProcessorHelper extends CommonNotificationProcessorTask implements NotificationProcessorHelper {

    private static final String EMAIL_TEXT = "Please use the below link to reset your password]\nLink: %s";
    private static final String EMAIL_SUBJECT = "Password reset";

    public PasswordResetProcessorHelper(Map<String, String> securedApplicationProperties) {
        super(securedApplicationProperties);
    }

    @Override
    public NotificationMessageType getProcessorType() {
        return NotificationMessageType.PASSWORD_RESET_EMAIL;
    }

    @Override
    public void processNotification(NotificationDto dto) {
        var emailDto = EmailDto.builder()
                .to(dto.getUserEmail())
                .text(getEmailText(dto))
                .subject(getEmailSubject())
                .build();

        sendMail(emailDto);
    }

    private String getEmailText(NotificationDto dto) {
        return String.format(EMAIL_TEXT, dto.getPasswordResetLink());
    }

    private String getEmailSubject() {
        return EMAIL_SUBJECT;
    }
}
