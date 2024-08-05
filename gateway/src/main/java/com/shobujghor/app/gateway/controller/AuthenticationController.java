package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.AuthenticationService;
import com.shobujghor.app.utility.request.authentication.LoginRequest;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import com.shobujghor.app.utility.response.authentication.LoginResponse;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/user/reset-password")
    public void resetUserPassword(@RequestBody PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        authenticationService.resetUserPassword(request, httpServletRequest);
    }

    @GetMapping("/user/change-password")
    public String validatePasswordResetToken(@RequestParam("token") String token) {
        return authenticationService.validatePasswordResetToken(token);
    }

    @PostMapping("/user/save-password")
    public boolean savePassword(@RequestBody SavePasswordRequest request) {
        return authenticationService.savePassword(request);
    }
}
