package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.CartClient;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartClient cartClient;

    @Override
    public AddToCartResponse addToCart(AddToCartRequest request) {
        return cartClient.addItemToCart(request);
    }
}
