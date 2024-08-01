package com.shobujghor.app.authentication.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.PasswordResetInfo;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetInfoRepository extends AbstractDynamoDbRepository<PasswordResetInfo> {
    public PasswordResetInfoRepository(DynamoDBMapper mapper) {
        super(mapper, PasswordResetInfo.class);
    }
}
