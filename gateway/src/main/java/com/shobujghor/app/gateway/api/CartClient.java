package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cartClient", url = "http://localhost:8005/cart")
public interface CartClient {
    @PostMapping("/add")
    AddToCartResponse addItemToCart(AddToCartRequest request);
}
