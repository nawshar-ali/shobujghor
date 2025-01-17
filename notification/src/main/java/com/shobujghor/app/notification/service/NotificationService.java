package com.shobujghor.app.notification.service;

import com.shobujghor.app.utility.request.notification.NotificationRequest;

public interface NotificationService {
    void processNotification(NotificationRequest request);
}
