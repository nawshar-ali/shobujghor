package com.shobujghor.app.notification.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProcessorServiceImpl implements NotificationProcessorService {

    private final NotificationProcessorFactory notificationProcessorFactory;

    @Override
    public NotificationProcessorHelper getNotificationProcessorHelper(String messageType) {
        return notificationProcessorFactory.getNotificationProcessorHelper(messageType);
    }
}
