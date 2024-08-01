package com.shobujghor.app.authentication.service;

import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface PasswordResetService {
    void resetUserPassword(PasswordResetRequest request, HttpServletRequest httpServletRequest);
}
