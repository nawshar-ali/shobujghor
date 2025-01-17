package com.shobujghor.app.authentication.controller;

import com.shobujghor.app.authentication.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam String token) {
        return emailVerificationService.verifyEmail(token);
    }
}
