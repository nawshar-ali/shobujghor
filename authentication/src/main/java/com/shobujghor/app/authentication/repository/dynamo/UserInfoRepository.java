package com.shobujghor.app.authentication.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepository extends AbstractDynamoDbRepository<UserInfo> {
    public UserInfoRepository(DynamoDBMapper mapper) {
        super(mapper, UserInfo.class);
    }
}
