package com.shobujghor.app.authentication.controller;

import com.shobujghor.app.authentication.service.PasswordResetService;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/user/reset-password")
    public void resetUserPassword(@RequestBody PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        passwordResetService.resetUserPassword(request, httpServletRequest);
    }
}
