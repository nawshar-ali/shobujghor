package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "authenticationClient", url = "http://localhost:8004/authentication")
public interface AuthenticationClient {
    @GetMapping("/auth/register")
    RegistrationResponse registerCustomer(RegistrationRequest request);
}
