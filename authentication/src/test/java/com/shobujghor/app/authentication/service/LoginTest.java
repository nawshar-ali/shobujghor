package com.shobujghor.app.authentication.service;

import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
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
class LoginTest {
    @Mock
    UserInfoRepository userInfoRepository;

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Test
    public void doLogin_should_throw_error_if_user_is_present() {
        var request = LoginRequest.builder().email("abc@gmail.com").build();

        Mockito.when(userInfoRepository.getData(request.getEmail()))
                .thenReturn(Optional.empty());

        var ex = Assertions.assertThrows(RuntimeException.class, () -> authenticationService.doLogin(request));
        Assertions.assertEquals(ErrorUtil.INVALID_CREDENTIALS, ex.getMessage());
    }
}