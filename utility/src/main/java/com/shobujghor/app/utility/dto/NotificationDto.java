package com.shobujghor.app.utility.dto;

import com.shobujghor.app.utility.constants.NotificationMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto implements Serializable {
    private NotificationMessageType messageType;
    private String passwordResetLink;
    private String userEmail;
}
