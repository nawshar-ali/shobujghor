package com.shobujghor.app.utility.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.shobujghor.app.utility.constants.Roles;
import com.shobujghor.app.utility.constants.TableNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = TableNames.USER_INFO_TABLE)
public class UserInfo {
    @DynamoDBHashKey
    private String email;

    @DynamoDBAttribute
    private String password;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    private String address;

    @DynamoDBAttribute
    private String mobileNumber;

    @DynamoDBAttribute
    private String profileImage;

    @DynamoDBAttribute
    @Builder.Default
    private List<String> roles = Arrays.asList(Roles.ROLE_USER);
}
