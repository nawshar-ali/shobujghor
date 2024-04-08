package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "authenticationClient", url = "http://localhost:8004/authentication")
public interface AuthenticationClient {
    @GetMapping("/auth/register")
    RegistrationResponse registerCustomer(RegistrationRequest request);

    @PostMapping("/auth/login")
    LoginResponse doLogin(LoginRequest request);
}
