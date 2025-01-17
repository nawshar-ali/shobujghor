package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.AuthenticationService;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public RegistrationResponse registerCustomer(@RequestBody @Valid RegistrationRequest request) {
        return authenticationService.registerCustomer(request);
    }

    @PostMapping("/login")
    public LoginResponse doLogin(@RequestBody @Valid LoginRequest request) {
        return authenticationService.doLogin(request);
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String token) {
        return authenticationService.verifyEmail(token);
    }
}
