package com.shobujghor.app.utility.repository.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.Error;

public class ErrorCodeRepository extends AbstractDynamoDbRepository<Error> {
    public ErrorCodeRepository(DynamoDBMapper mapper) {
        super(mapper, Error.class);
    }
}
