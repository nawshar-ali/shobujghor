package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.AuthenticationClient;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationClient authenticationClient;

    @Override
    public RegistrationResponse registerCustomer(RegistrationRequest request) {
        return authenticationClient.registerCustomer(request);
    }

    @Override
    public LoginResponse doLogin(LoginRequest request) {
        return authenticationClient.doLogin(request);
    }

    @Override
    public void resetUserPassword(PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        authenticationClient.resetUserPassword(request, httpServletRequest);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        return authenticationClient.validatePasswordResetToken(token);
    }

    @Override
    public boolean savePassword(SavePasswordRequest request) {
        return authenticationClient.savePassword(request);
    }
}
