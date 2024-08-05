package com.shobujghor.app.authentication.service;

import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface PasswordResetService {
    void resetUserPassword(PasswordResetRequest request, HttpServletRequest httpServletRequest);

    String validatePasswordResetToken(String token);

    boolean savePassword(SavePasswordRequest request);
}
