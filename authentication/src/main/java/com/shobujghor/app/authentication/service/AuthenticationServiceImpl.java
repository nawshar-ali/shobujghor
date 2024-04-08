package com.shobujghor.app.authentication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.models.UserInfo;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserInfoRepository userInfoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public RegistrationResponse registerCustomer(RegistrationRequest request) {
        var userInfoOpt = userInfoRepository.getData(request.getEmail());

        if (userInfoOpt.isPresent()) {
            log.error("user already exists with following email: {}", request.getEmail());
            throw new RuntimeException(ErrorUtil.USER_EXISTS);
        }

        var userInfo = objectMapper.convertValue(request, UserInfo.class);
        userInfoRepository.saveData(userInfo);

        return RegistrationResponse.builder().email(request.getEmail()).build();
    }
}
