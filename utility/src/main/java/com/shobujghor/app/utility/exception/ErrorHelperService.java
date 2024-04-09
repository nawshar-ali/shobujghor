package com.shobujghor.app.utility.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.models.Error;
import com.shobujghor.app.utility.repository.dynamo.ErrorCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RequiredArgsConstructor
public class ErrorHelperService {
    private final ErrorCodeRepository errorCodeRepository;
    private final ObjectMapper objectMapper;
    private static final String DEFAULT_ERROR_MESSAGE = "UNKNOWN_ERROR_OCCURRED";

    private static ApplicationException apply(Error error) {
        return new ApplicationException(error.getCode(), error.getMessage(), HttpStatus.resolve(error.getStatusCode()));
    }

    public ApplicationException buildExceptionFromCode(String code) {

        return getErrorCode(code)
                .map(ErrorHelperService::apply)
                .orElseGet(() -> apply(getDefaultErrorCode(code)));
    }

    private Optional<Error> getErrorCode(String code) {

        return errorCodeRepository.getData(code)
                .map(error -> objectMapper.convertValue(error, Error.class));

    }

    private Error getDefaultErrorCode(String code) {
        return Error.builder()
                .code(code)
                .message(DEFAULT_ERROR_MESSAGE)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
