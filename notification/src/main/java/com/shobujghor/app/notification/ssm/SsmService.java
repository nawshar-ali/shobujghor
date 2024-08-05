package com.shobujghor.app.notification.ssm;

import com.shobujghor.app.utility.constants.Profiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

@Service
@Slf4j
public class SsmService {
    private static final boolean DECRYPTION_STATUS = true;

    public String getSsmParamValue(SsmClient ssmClient, String paramKey) {
        try {
            var getParameterRequest = Profiles.DEV.name().equalsIgnoreCase(paramKey) ?
                    GetParameterRequest.builder()
                            .name(paramKey)
                            .build() :
                    GetParameterRequest.builder()
                            .name(paramKey)
                            .withDecryption(DECRYPTION_STATUS)
                            .build();

            return ssmClient.getParameter(getParameterRequest).parameter().value();
        } catch (Exception e) {
            log.error("Failed to load ssm {}", paramKey, e);
        }
        return null;
    }
}
