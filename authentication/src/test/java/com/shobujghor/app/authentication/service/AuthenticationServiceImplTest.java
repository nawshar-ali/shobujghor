package com.shobujghor.app.authentication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.utility.exception.ApplicationException;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.models.UserInfo;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import com.shobujghor.app.utility.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserInfoRepository mockUserInfoRepository;
    @Mock
    private JWTUtil mockJwtUtil;
    @Mock
    private ErrorHelperService mockErrorHelperService;

    private AuthenticationServiceImpl authenticationServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        authenticationServiceImplUnderTest = new AuthenticationServiceImpl(mockUserInfoRepository, new ObjectMapper(),
                mockJwtUtil, mockErrorHelperService);
        ReflectionTestUtils.setField(authenticationServiceImplUnderTest, "tokenExpirationInSecs", "3600");
    }

    @Test
    void testRegisterCustomer_ThrowsApplicationException() {
        // Setup
        final RegistrationRequest request = RegistrationRequest.builder()
                .email("email")
                .build();

        // Configure UserInfoRepository.getData(...).
        final Optional<UserInfo> userInfo = Optional.of(UserInfo.builder()
                .email("email")
                .password("password")
                .build());
        when(mockUserInfoRepository.getData("email")).thenReturn(userInfo);

        // Configure ErrorHelperService.buildExceptionFromCode(...).
        final ApplicationException applicationException = new ApplicationException("errorCode", "message",
                HttpStatus.OK);
        when(mockErrorHelperService.buildExceptionFromCode("USER_EXISTS")).thenReturn(applicationException);

        // Run the test
        assertThatThrownBy(() -> authenticationServiceImplUnderTest.registerCustomer(request))
                .isInstanceOf(ApplicationException.class);
    }

    @Test
    void testRegisterCustomer_UserInfoRepositoryGetDataReturnsAbsent() {
        // Setup
        final RegistrationRequest request = RegistrationRequest.builder()
                .email("email")
                .password("password")
                .build();
        final RegistrationResponse expectedResult = RegistrationResponse.builder()
                .email("email")
                .build();
        when(mockUserInfoRepository.getData("email")).thenReturn(Optional.empty());

        // Run the test
        final RegistrationResponse result = authenticationServiceImplUnderTest.registerCustomer(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserInfoRepository).saveData(UserInfo.builder()
                .email("email")
                .password("password")
                .build());
    }

    @Test
    void testDoLogin() {
        // Setup
        final LoginRequest request = LoginRequest.builder()
                .email("email")
                .password("password")
                .build();
        final LoginResponse expectedResult = LoginResponse.builder()
                .accessToken("accessToken")
                .expiresIn("3600")
                .build();

        // Configure UserInfoRepository.getData(...).
        final Optional<UserInfo> userInfo = Optional.of(UserInfo.builder()
                .email("email")
                .password("password")
                .build());
        when(mockUserInfoRepository.getData("email")).thenReturn(userInfo);

        when(mockJwtUtil.generateToken("email")).thenReturn("accessToken");

        // Run the test
        final LoginResponse result = authenticationServiceImplUnderTest.doLogin(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDoLogin_UserInfoRepositoryReturnsAbsent() {
        // Setup
        final LoginRequest request = LoginRequest.builder()
                .email("email")
                .password("password")
                .build();
        when(mockUserInfoRepository.getData("email")).thenReturn(Optional.empty());

        // Configure ErrorHelperService.buildExceptionFromCode(...).
        final ApplicationException applicationException = new ApplicationException("errorCode", "message",
                HttpStatus.OK);
        when(mockErrorHelperService.buildExceptionFromCode("INVALID_CREDENTIALS")).thenReturn(applicationException);

        // Run the test
        assertThatThrownBy(() -> authenticationServiceImplUnderTest.doLogin(request))
                .isInstanceOf(ApplicationException.class);
    }
}
