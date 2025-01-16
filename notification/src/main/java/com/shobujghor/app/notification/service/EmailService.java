package com.shobujghor.app.notification.service;

import com.shobujghor.app.utility.request.notification.SendEmailRequest;

public interface EmailService {
    boolean sendEmail(SendEmailRequest request);
}
