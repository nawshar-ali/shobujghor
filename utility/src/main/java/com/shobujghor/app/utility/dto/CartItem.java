package com.shobujghor.app.utility.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class CartItem implements Serializable {
    private String itemName;
    private String quantity;
    private String totalPrice;
    private String totalDiscount;
    private String actualPrice;
}
