package com.shobujghor.app.authentication.service;

import com.shobujghor.app.utility.request.authentication.PasswordResetRequest;
import com.shobujghor.app.utility.request.authentication.SavePasswordRequest;

public interface PasswordResetService {
    void resetUserPassword(PasswordResetRequest request);

    String validatePasswordResetToken(String token);

    boolean savePassword(SavePasswordRequest request);
}
