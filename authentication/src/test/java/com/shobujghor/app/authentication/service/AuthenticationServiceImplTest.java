package com.shobujghor.app.authentication.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.models.UserInfo;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles(value = "dev")
class AuthenticationServiceImplTest {
    @Mock
    UserInfoRepository userInfoRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Test
    public void register_customer_throws_exception_when_user_already_exists() {
        var request = RegistrationRequest.builder().email("abc@gmail.com").build();
        var optUser = UserInfo.builder().email("abc@gmail.com").build();

        Mockito.when(userInfoRepository.getData(request.getEmail()))
                .thenReturn(Optional.of(optUser));

        var ex = Assertions.assertThrows(RuntimeException.class, () -> authenticationService.registerCustomer(request));

        Assertions.assertEquals(ErrorUtil.USER_EXISTS, ex.getMessage());
    }

    @Test
    public void register_customer_returns_successful_registration_response() {
        var request = RegistrationRequest.builder().email("abc@gmail.com").build();

        Mockito.when(userInfoRepository.getData(request.getEmail()))
                .thenReturn(Optional.empty());

        var response = authenticationService.registerCustomer(request);

        Assertions.assertEquals(request.getEmail(), response.getEmail());
    }
}