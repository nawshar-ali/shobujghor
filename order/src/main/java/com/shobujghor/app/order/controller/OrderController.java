package com.shobujghor.app.order.controller;

import com.shobujghor.app.order.service.OrderService;
import com.shobujghor.app.utility.request.order.OrderListRequest;
import com.shobujghor.app.utility.response.order.OrderListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/list")
    public OrderListResponse getOrders(@RequestBody OrderListRequest request) {
        return orderService.getOrderList(request);
    }
}
