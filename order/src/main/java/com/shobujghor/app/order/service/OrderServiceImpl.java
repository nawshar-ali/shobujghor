package com.shobujghor.app.order.service;

import com.shobujghor.app.order.repository.OrderRepository;
import com.shobujghor.app.utility.models.Order;
import com.shobujghor.app.utility.request.order.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

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
    }
}
