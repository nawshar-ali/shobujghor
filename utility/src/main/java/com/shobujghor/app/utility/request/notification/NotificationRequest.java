package com.shobujghor.app.utility.request.notification;

import com.shobujghor.app.utility.constants.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest implements Serializable {
    private String message;
    private String receiverEmail;
    private String emailVerificationToken;
    private NotificationType type;
}
