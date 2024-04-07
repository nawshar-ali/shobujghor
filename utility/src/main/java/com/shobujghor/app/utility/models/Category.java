package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
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
@DynamoDBTable(tableName = TableNames.CATEGORY_TABLE)
public class Category {
    @DynamoDBHashKey
    private String name;

    @DynamoDBAttribute
    private String shortDescription;

    @DynamoDBAttribute
    private String longDescription;

    @DynamoDBAttribute
    private String logoUrl;
}
