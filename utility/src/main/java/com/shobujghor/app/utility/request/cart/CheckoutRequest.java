package com.shobujghor.app.utility.request.cart;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest implements Serializable {
    @NotNull
    private String customerEmail;

    @NotNull
    private String cartId;

    @NotNull
    private String deliveryAddress;
}
