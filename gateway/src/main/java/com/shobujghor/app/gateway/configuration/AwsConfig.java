package com.shobujghor.app.gateway.configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.shobujghor.app.utility.constants.AwsEndpoints;
import com.shobujghor.app.utility.constants.Profiles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.PaginationLoadingStrategy.ITERATION_ONLY;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final Environment environment;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {

        var profile = environment.getActiveProfiles()[0];

        var client = Profiles.DEV.name().equalsIgnoreCase(profile) ? AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AwsEndpoints.DYNAMO_DB_ENDPOINT, Regions.AP_SOUTHEAST_1.getName()))
                .build() : AmazonDynamoDBClientBuilder.standard().build();

        var mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(profile))
                .withPaginationLoadingStrategy(ITERATION_ONLY)
                .build();

        return new DynamoDBMapper(client, mapperConfig);
    }
}
