package com.shobujghor.app.utility.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest implements Serializable {
    private String orderId;
    private String customerEmail;
    private List<String> items;
    private String originalPrice;
    private String discountPrice;
    private String actualPrice;
}
