package com.shobujghor.app.utility.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {
    private final String errorCode;
    private final String message;
    private final Integer statusCode;
    private final String path;
    private final String method;

    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
}
