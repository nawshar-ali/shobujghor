package com.shobujghor.app.gateway.service;

import com.shobujghor.app.utility.request.order.OrderListRequest;
import com.shobujghor.app.utility.response.order.OrderListResponse;

public interface OrderService {
    OrderListResponse getOrderList(OrderListRequest request);
}
