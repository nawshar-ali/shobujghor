package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.OrderClient;
import com.shobujghor.app.utility.request.order.OrderListRequest;
import com.shobujghor.app.utility.response.order.OrderListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderClient orderClient;

    @Override
    public OrderListResponse getOrderList(OrderListRequest request) {
        return orderClient.getOrders(request);
    }
}
