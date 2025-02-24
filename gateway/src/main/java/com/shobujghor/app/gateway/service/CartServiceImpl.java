package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.CartClient;
import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.cart.CheckoutRequest;
import com.shobujghor.app.utility.request.cart.ViewCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.response.cart.CheckoutResponse;
import com.shobujghor.app.utility.response.cart.ViewCartResponse;
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

    @Override
    public CheckoutResponse checkout(CheckoutRequest request) {
        return cartClient.checkout(request);
    }

    @Override
    public ViewCartResponse viewCart(ViewCartRequest request) {
        return cartClient.viewCart(request);
    }
}
