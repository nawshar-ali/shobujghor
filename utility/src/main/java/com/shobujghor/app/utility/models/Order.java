package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.shobujghor.app.utility.constants.OrderStatus;
import com.shobujghor.app.utility.constants.TableNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.ORDER_INFO_TABLE)
public class Order {
    @DynamoDBHashKey
    private String customerEmail;

    @DynamoDBRangeKey
    private String id;

    @DynamoDBAttribute
    private String originalPrice;

    @DynamoDBAttribute
    private String discountPrice;

    @DynamoDBAttribute
    private String actualPrice;

    @DynamoDBAttribute
    private String orderDateTime;

    @DynamoDBAttribute
    private String deliveryAddress;

    @DynamoDBAttribute
    private List<String> items;

    @DynamoDBAttribute
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;
}
