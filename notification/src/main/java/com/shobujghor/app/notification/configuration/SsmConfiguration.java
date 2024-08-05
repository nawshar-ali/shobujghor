package com.shobujghor.app.notification.configuration;

import com.shobujghor.app.notification.ssm.SsmKeys;
import com.shobujghor.app.notification.ssm.SsmService;
import com.shobujghor.app.utility.constants.AwsEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SsmConfiguration {
    private final SsmClient ssmClient;
    @Value("${ssm.prefix}")
    private String ssmPrefix;
    @Value("${mail.username.key}")
    private String emailUsernameKey;
    @Value("${mail.password.key}")
    private String emailPasswordKey;

    @Bean
    @Qualifier(value = "securedApplicationProperties")
    public Map<String, String> getSecuredApplicationProperties() {
        var ssmService = new SsmService();
        Map<String, String> securedApplicationProperties = new HashMap<>();
        securedApplicationProperties.put(SsmKeys.EMAIL_USERNAME_KEY, ssmService.getSsmParamValue(ssmClient, ssmPrefix + emailUsernameKey));
        securedApplicationProperties.put(SsmKeys.EMAIL_PASSWORD_KEY, ssmService.getSsmParamValue(ssmClient, ssmPrefix + emailPasswordKey));
        return securedApplicationProperties;
    }

    @Bean
    public SsmClient ssmClient() {
        return SsmClient.builder()
                .endpointOverride(java.net.URI.create(AwsEndpoints.SSM_ENDPOINT))
                .region(Region.US_EAST_1)
                .build();
    }
}
