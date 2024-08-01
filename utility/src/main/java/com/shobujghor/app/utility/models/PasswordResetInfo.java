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
@DynamoDBTable(tableName = TableNames.PASSWORD_RESET_INFO_TABLE)
public class PasswordResetInfo {
    @DynamoDBHashKey
    private String token;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String passwordResetLink;
}
