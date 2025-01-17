package com.shobujghor.app.notification.configuration;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.repository.dynamo.ErrorCodeRepository;
import com.shobujghor.app.utility.ssm.SsmKeys;
import com.shobujghor.app.utility.ssm.SsmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final SsmClient ssmClient;

    @Value("${ssm.prefix}")
    private String ssmPrefix;

    @Value("${mail.username.key}")
    private String emailUsernameKey;

    @Value("${mail.password.key}")
    private String emailPasswordKey;

    @Bean
    ErrorCodeRepository errorCodeRepository(DynamoDBMapper dynamoDBMapper) {
        return new ErrorCodeRepository(dynamoDBMapper);
    }

    @Bean
    ErrorHelperService errorHelperService(ErrorCodeRepository errorCodeRepository, ObjectMapper objectMapper) {
        return new ErrorHelperService(errorCodeRepository, objectMapper);
    }

    @Bean
    @Qualifier(value = "securedApplicationProperties")
    public Map<String, String> getSecuredApplicationProperties() {
        var ssmService = new SsmService();
        Map<String, String> securedApplicationProperties = new HashMap<>();
        securedApplicationProperties.put(SsmKeys.EMAIL_USERNAME_KEY, ssmService.getSsmParamValue(ssmClient, ssmPrefix + emailUsernameKey));
        securedApplicationProperties.put(SsmKeys.EMAIL_PASSWORD_KEY, ssmService.getSsmParamValue(ssmClient, ssmPrefix + emailPasswordKey));
        return securedApplicationProperties;
    }
}
