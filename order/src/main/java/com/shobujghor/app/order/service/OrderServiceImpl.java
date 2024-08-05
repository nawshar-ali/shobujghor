package com.shobujghor.app.order.service;

import com.google.gson.Gson;
import com.shobujghor.app.order.repository.OrderRepository;
import com.shobujghor.app.utility.constants.NotificationMessageType;
import com.shobujghor.app.utility.dto.NotificationDto;
import com.shobujghor.app.utility.models.Order;
import com.shobujghor.app.utility.request.order.PlaceOrderRequest;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SqsTemplate sqsTemplate;
    private final Gson gson;

    @Value("${notification.queue}")
    private String notificationQueue;

    @Override
    public void placeOrder(PlaceOrderRequest request) {
        var oder = Order.builder()
                .id(request.getOrderId())
                .customerEmail(request.getCustomerEmail())
                .items(request.getItems())
                .deliveryAddress(request.getDeliveryAddress())
                .orderDateTime(request.getOrderDateTime())
                .originalPrice(request.getOriginalPrice())
                .discountPrice(request.getDiscountPrice())
                .actualPrice(request.getActualPrice())
                .build();
        orderRepository.saveData(oder);
        publishOrderConfirmEvent(request);
    }

    private void publishOrderConfirmEvent(PlaceOrderRequest request) {
        var notificationDto = NotificationDto.builder()
                .messageType(NotificationMessageType.ORDER_CONFIRM)
                .userEmail(request.getCustomerEmail())
                .build();

        var sqsPayload = gson.toJson(notificationDto);

        try {
            sqsTemplate.sendAsync(sqsSendOptions -> sqsSendOptions
                    .queue(notificationQueue)
                    .payload(sqsPayload)
                    .messageGroupId(request.getOrderId()));
        } catch (Exception e) {
            log.error("orderId: {} | Failed to publish event in notification queue for password reset", request.getOrderId(), e);
        }
    }
}
