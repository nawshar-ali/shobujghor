package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.shobujghor.app.utility.constants.TableNames;
import com.shobujghor.app.utility.dto.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.CART_INFO_TABLE)
public class Cart {
    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    @Builder.Default
    private List<CartItem> itemList = new ArrayList<>();

    @DynamoDBAttribute
    @Builder.Default
    private String totalBillAmount = "0.0";

    @DynamoDBAttribute
    @Builder.Default
    private String totalDiscountAmount = "0.0";

    @DynamoDBAttribute
    @Builder.Default
    private String amountToBePaid = "0.0";
}
