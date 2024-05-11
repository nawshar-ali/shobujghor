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
public class AddToCartRequest implements Serializable {
    @NotNull
    private String itemName;

    @NotNull
    private String categoryName;

    @NotNull
    private String quantity;

    private String cartId;
}
