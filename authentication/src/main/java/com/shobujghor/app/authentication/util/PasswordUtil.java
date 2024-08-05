package com.shobujghor.app.authentication.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static String getEncryptedPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String encryptedPassword = encoder.encode(rawPassword);
        return encryptedPassword;
    }
}
