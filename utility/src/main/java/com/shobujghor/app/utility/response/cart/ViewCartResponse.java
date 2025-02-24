package com.shobujghor.app.utility.response.cart;

import com.shobujghor.app.utility.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCartResponse implements Serializable {
    private String id;

    @Builder.Default
    private String totalBillAmount = "0.00";

    @Builder.Default
    private String totalDiscountAmount = "0.00";

    @Builder.Default
    private String amountToBePaid = "0.00";

    @Builder.Default
    private List<CartItem> itemList = new ArrayList<>();
}
