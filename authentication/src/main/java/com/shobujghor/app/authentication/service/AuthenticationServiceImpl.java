package com.shobujghor.app.authentication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.authentication.repository.dynamo.UserInfoRepository;
import com.shobujghor.app.authentication.util.PasswordUtil;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.util.JWTUtil;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.utility.models.UserInfo;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserInfoRepository userInfoRepository;
    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final ErrorHelperService errorHelperService;

    @Value("${jwt.expiration}")
    private String tokenExpirationInSecs;

    @Override
    public RegistrationResponse registerCustomer(RegistrationRequest request) {
        var userInfoOpt = userInfoRepository.getData(request.getEmail());

        if (userInfoOpt.isPresent()) {
            log.error("user already exists with following email: {}", request.getEmail());
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.USER_EXISTS);
        }

        var userInfo = objectMapper.convertValue(request, UserInfo.class);
        userInfo.setPassword(PasswordUtil.getEncryptedPassword(request.getPassword()));

        userInfoRepository.saveData(userInfo);

        return RegistrationResponse.builder().email(request.getEmail()).build();
    }

    @Override
    public LoginResponse doLogin(LoginRequest request) {
        var userInfoOpt = userInfoRepository.getData(request.getEmail());
        if (userInfoOpt.isPresent()) {
            validateCredentials(request, userInfoOpt.get());
            var accessToken = jwtUtil.generateToken(request.getEmail());
            return LoginResponse.builder().accessToken(accessToken).expiresIn(tokenExpirationInSecs).build();
        } else {
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.INVALID_CREDENTIALS);
        }
    }

    private void validateCredentials(LoginRequest request, UserInfo userInfo) {
        var encryptedPassword = PasswordUtil.getEncryptedPassword(request.getPassword());

        if (!request.getEmail().equals(userInfo.getEmail())
        || !encryptedPassword.equals(userInfo.getPassword())) {
            log.error("Credentials does not match | email: {}", request.getEmail());
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.INVALID_CREDENTIALS);
        }
    }
}
