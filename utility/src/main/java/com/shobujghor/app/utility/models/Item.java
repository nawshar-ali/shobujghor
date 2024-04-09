package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.shobujghor.app.utility.constants.TableNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.ITEM_INFO_TABLE)
public class Item {
    @DynamoDBHashKey
    private String categoryName;

    @DynamoDBRangeKey
    private String name;

    @DynamoDBAttribute
    private String shortDescription;

    @DynamoDBAttribute
    private String longDescription;

    @DynamoDBAttribute
    private String logoUrl;

    @DynamoDBAttribute
    private String price;
}
