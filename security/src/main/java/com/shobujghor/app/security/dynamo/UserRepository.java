package com.shobujghor.app.security.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractDynamoDbRepository<UserInfo> {
    public UserRepository(DynamoDBMapper mapper) {
        super(mapper, UserInfo.class);
    }
}
