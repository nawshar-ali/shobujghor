package com.shobujghor.app.authentication.service;

import com.shobujghor.app.utility.request.authentication.RegistrationRequest;
import com.shobujghor.app.utility.response.authentication.RegistrationResponse;

public interface AuthenticationService {
    RegistrationResponse registerCustomer(RegistrationRequest request);
}
