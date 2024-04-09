package com.shobujghor.app.cart.service;

import com.shobujghor.app.utility.request.cart.AddToCartRequest;
import com.shobujghor.app.utility.response.cart.AddToCartResponse;

public interface CartService {
    AddToCartResponse addItemToCart(AddToCartRequest request);
}
