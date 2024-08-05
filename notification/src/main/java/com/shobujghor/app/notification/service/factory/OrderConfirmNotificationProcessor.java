package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.EmailDto;
import com.shobujghor.app.utility.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("OrderConfirmNotificationProcessor")
public class OrderConfirmNotificationProcessor extends CommonNotificationProcessorTask implements NotificationProcessorHelper {

    private static final String EMAIL_TEXT = "Order: %s has been confirmed.\nThanks for staying with shobujghor";
    private static final String EMAIL_SUBJECT = "Order confirm";

    public OrderConfirmNotificationProcessor(Map<String, String> securedApplicationProperties) {
        super(securedApplicationProperties);
    }

    @Override
    public NotificationMessageType getProcessorType() {
        return NotificationMessageType.ORDER_CONFIRM;
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
