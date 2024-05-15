package com.shobujghor.app.cart.configuration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.repository.dynamo.ErrorCodeRepository;
import io.awspring.cloud.sqs.listener.QueueNotFoundStrategy;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AppConfig {

    @Bean
    ErrorCodeRepository errorCodeRepository(DynamoDBMapper dynamoDBMapper) {
        return new ErrorCodeRepository(dynamoDBMapper);
    }

    @Bean
    ErrorHelperService errorHelperService(ErrorCodeRepository errorCodeRepository, ObjectMapper objectMapper) {
        return new ErrorHelperService(errorCodeRepository, objectMapper);
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(o -> o.queueNotFoundStrategy(QueueNotFoundStrategy.FAIL))
                .build();

    }
}
