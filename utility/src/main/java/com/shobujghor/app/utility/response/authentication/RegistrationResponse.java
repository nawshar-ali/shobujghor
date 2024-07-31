package com.shobujghor.app.utility.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse implements Serializable {
    private String email;

    //@Builder.Default
    //private String updateTime = Instant.now().toString();
}
