package com.shobujghor.app.authentication.controller;

import com.shobujghor.app.authentication.service.PasswordResetService;
import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/user/reset-password")
    public void resetUserPassword(@RequestBody PasswordResetRequest request, HttpServletRequest httpServletRequest) {
        passwordResetService.resetUserPassword(request, httpServletRequest);
    }

    @GetMapping("/user/change-password")
    public String validatePasswordResetToken(@RequestParam("token") String token) {
        return passwordResetService.validatePasswordResetToken(token);
    }

    @PostMapping("/user/save-password")
    public boolean savePassword(@RequestBody SavePasswordRequest request) {
        return passwordResetService.savePassword(request);
    }
}
