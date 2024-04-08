package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.AuthenticationClient;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
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
}
