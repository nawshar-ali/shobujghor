package com.shobujghor.app.authentication.service;

import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse registerCustomer(RegistrationRequest request);

    LoginResponse doLogin(LoginRequest request);
}
