package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.order.OrderListRequest;
import com.shobujghor.app.utility.response.order.OrderListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "orderClient", url = "http://localhost:8006/order")
public interface OrderClient {
    @PostMapping("/list")
    OrderListResponse getOrders(OrderListRequest request);
}
