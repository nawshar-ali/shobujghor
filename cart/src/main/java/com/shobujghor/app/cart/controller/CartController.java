package com.shobujghor.app.cart.controller;

import com.shobujghor.app.cart.service.CartService;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.cart.CheckoutRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.response.cart.CheckoutResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public AddToCartResponse addItemToCart(@RequestBody @Valid AddToCartRequest request) {
        return cartService.addItemToCart(request);
    }

    @PostMapping("/checkout")
    public CheckoutResponse checkout(@RequestBody @Valid CheckoutRequest request) {
        return cartService.checkout(request);
    }
}
