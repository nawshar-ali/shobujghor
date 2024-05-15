package com.shobujghor.app.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto implements Serializable {
    private String itemName;
    private String originalPrice;
    private String discountPrice;
    private String actualPrice;
}
