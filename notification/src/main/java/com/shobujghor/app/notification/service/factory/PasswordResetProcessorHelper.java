package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.NotificationDto;
import org.springframework.stereotype.Service;

@Service("PasswordResetProcessorHelper")
public class PasswordResetProcessorHelper implements NotificationProcessorHelper {

    @Override
    public NotificationMessageType getProcessorType() {
        return NotificationMessageType.PASSWORD_RESET_EMAIL;
    }

    @Override
    public void processNotification(NotificationDto dto) {

    }
}
