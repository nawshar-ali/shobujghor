package com.shobujghor.app.order.sqs;

import com.google.gson.Gson;
import com.shobujghor.app.order.service.OrderService;
import com.shobujghor.app.utility.request.order.PlaceOrderRequest;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderListener {

    private final OrderService orderService;
    private final Gson gson;

    @SqsListener("${order.queue}")
    private void receiveMessage(String payload) {
        var request = gson.fromJson(payload, PlaceOrderRequest.class);
        orderService.placeOrder(request);
    }

}
