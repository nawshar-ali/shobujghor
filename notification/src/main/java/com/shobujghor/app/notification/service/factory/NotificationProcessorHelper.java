package com.shobujghor.app.notification.service.factory;

import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.NotificationDto;

public interface NotificationProcessorHelper {
    NotificationMessageType getProcessorType();
    void processNotification(NotificationDto dto);
}
