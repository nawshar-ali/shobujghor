package com.shobujghor.app.gateway.service;

import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.cart.CheckoutRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.response.cart.CheckoutResponse;

public interface CartService {
    AddToCartResponse addToCart(AddToCartRequest request);

    CheckoutResponse checkout(CheckoutRequest request);
}
