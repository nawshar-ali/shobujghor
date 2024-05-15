package com.shobujghor.app.utility.response.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse implements Serializable {
    private boolean success;
    private String orderId;
}
