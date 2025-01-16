package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.shobujghor.app.utility.constants.TableNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.EMAIL_VERIFICATION_TOKEN_TABLE)
public class EmailVerificationToken {

    @DynamoDBHashKey
    private String token;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private LocalDateTime tokenExpiryDate;
}
