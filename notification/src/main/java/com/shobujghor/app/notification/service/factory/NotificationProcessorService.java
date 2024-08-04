package com.shobujghor.app.notification.service.factory;

public interface NotificationProcessorService {
    NotificationProcessorHelper getNotificationProcessorHelper(String messageType);
}
