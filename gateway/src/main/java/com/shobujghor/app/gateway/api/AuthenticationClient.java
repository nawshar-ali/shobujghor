package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "authenticationClient", url = "http://localhost:8004/authentication")
public interface AuthenticationClient {
    @GetMapping("/auth/register")
    RegistrationResponse registerCustomer(RegistrationRequest request);

    @PostMapping("/auth/login")
    LoginResponse doLogin(LoginRequest request);

    @PostMapping("/user/reset-password")
    void resetUserPassword(PasswordResetRequest request);

    @PostMapping("/user/change-password")
    String validatePasswordResetToken(String token);

    @PostMapping("/user/save-password")
    boolean savePassword(SavePasswordRequest request);
}
