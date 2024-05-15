package com.shobujghor.app.order.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @SqsListener("${order.queue}")
    private void receiveMessage(String payload) {
        log.info("Received message: {}", payload);
    }

}
