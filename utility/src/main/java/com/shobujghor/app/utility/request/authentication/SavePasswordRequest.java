package com.shobujghor.app.utility.request.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavePasswordRequest implements Serializable {
    private String email;
    private String password;
}
