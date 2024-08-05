package com.shobujghor.app.notification.sqs;

import com.google.gson.Gson;
import com.shobujghor.app.notification.service.factory.NotificationProcessorService;
import com.shobujghor.app.utility.dto.NotificationDto;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationProcessorService notificationProcessorService;
    private final Gson gson;

    @SqsListener("${notification.queue}")
    private void receiveMessage(String payload) {
        var dto = gson.fromJson(payload, NotificationDto.class);

        var helper = notificationProcessorService.getNotificationProcessorHelper(dto.getMessageType().name());

        helper.processNotification(dto);
    }

}
