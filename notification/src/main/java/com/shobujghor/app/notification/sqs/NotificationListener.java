package com.shobujghor.app.notification.sqs;

import com.google.gson.Gson;
import com.shobujghor.app.notification.service.NotificationService;
import com.shobujghor.app.utility.request.notification.NotificationRequest;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;
    private final Gson gson;

    @SqsListener("${notification.queue}")
    private void receiveMessage(String payload) {
        var request = gson.fromJson(payload, NotificationRequest.class);
        notificationService.processNotification(request);
    }

}
