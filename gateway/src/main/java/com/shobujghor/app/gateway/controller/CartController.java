package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.CartService;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    AddToCartResponse addToCart(@RequestBody @Valid AddToCartRequest request) {
        return cartService.addToCart(request);
    }
}
