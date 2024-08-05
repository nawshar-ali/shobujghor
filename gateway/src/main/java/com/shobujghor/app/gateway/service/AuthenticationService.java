package com.shobujghor.app.gateway.service;

import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    RegistrationResponse registerCustomer(RegistrationRequest request);

    LoginResponse doLogin(LoginRequest request);

    void resetUserPassword(PasswordResetRequest request, HttpServletRequest httpServletRequest);

    String validatePasswordResetToken(String token);

    boolean savePassword(SavePasswordRequest request);
}
