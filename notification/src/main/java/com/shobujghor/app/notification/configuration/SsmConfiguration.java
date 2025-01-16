package com.shobujghor.app.notification.configuration;

import com.shobujghor.app.utility.constants.AwsEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class SsmConfiguration {

    @Bean
    public SsmClient ssmClient() {
        return SsmClient.builder()
                .endpointOverride(URI.create(AwsEndpoints.SSM_ENDPOINT))
                .region(Region.US_EAST_1)
                .build();
    }
}
