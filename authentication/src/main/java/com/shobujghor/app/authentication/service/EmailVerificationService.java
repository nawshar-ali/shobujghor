package com.shobujghor.app.authentication.service;

public interface EmailVerificationService {
    String verifyEmail(String token);
}
