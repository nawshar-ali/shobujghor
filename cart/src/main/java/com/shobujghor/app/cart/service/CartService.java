package com.shobujghor.app.cart.service;

import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.request.cart.CheckoutRequest;
import com.shobujghor.app.utility.request.cart.ViewCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;
import com.shobujghor.app.utility.response.cart.CheckoutResponse;
import com.shobujghor.app.utility.response.cart.ViewCartResponse;
import jakarta.validation.Valid;

public interface CartService {
    AddToCartResponse addItemToCart(AddToCartRequest request);

    CheckoutResponse checkout(CheckoutRequest request);

    ViewCartResponse viewCart(@Valid ViewCartRequest request);
}
