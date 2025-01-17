package com.shobujghor.app.utility.request.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailRequest implements Serializable {
    private String receiverEmail;
    private String subject;
    private String body;
}
