package com.shobujghor.app.utility.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;
    private final ErrorHelperService errorHelperService;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleApplicationException(Exception exception, HttpServletRequest request) {

        if (exception instanceof ApplicationException) {
            var ex = (ApplicationException) exception;

            var response = ApiErrorResponse.builder()
                    .errorCode(ex.getErrorCode())
                    .message(ex.getMessage())
                    .path(request.getServletPath())
                    .statusCode(ex.getHttpStatus().value())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof FeignException) {
            var apiErrorResponseOpt = extractErrorResponse((FeignException) exception);

            if (apiErrorResponseOpt.isPresent()) {
                var ex = apiErrorResponseOpt.get();

                var response = ApiErrorResponse.builder()
                        .errorCode(ex.getErrorCode())
                        .message(ex.getMessage())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .statusCode(ex.getStatusCode())
                        .build();

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }

        throw errorHelperService.buildExceptionFromCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    private Optional<ApiErrorResponse> extractErrorResponse(FeignException ex) {
        try {
            return Optional.ofNullable(objectMapper.readValue(ex.contentUTF8(), ApiErrorResponse.class));
        } catch (Exception e) {
            log.error("FeignException message Not Of Type ApiErrorResponse");
        }
        return Optional.empty();
    }
}
