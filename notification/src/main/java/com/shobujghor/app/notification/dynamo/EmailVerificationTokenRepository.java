package com.shobujghor.app.notification.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.shobujghor.app.utility.dynamo.AbstractDynamoDbRepository;
import com.shobujghor.app.utility.models.EmailVerificationToken;
import org.springframework.stereotype.Repository;

@Repository
public class EmailVerificationTokenRepository extends AbstractDynamoDbRepository<EmailVerificationToken> {

    public EmailVerificationTokenRepository(DynamoDBMapper mapper) {
        super(mapper, EmailVerificationToken.class);
    }
}
