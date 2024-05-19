package com.shobujghor.app.order.configuration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.repository.dynamo.ErrorCodeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
