package com.shobujghor.app.order.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.exception.CommonExceptionHandler;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends CommonExceptionHandler {
    public ApplicationExceptionHandler(ObjectMapper objectMapper, ErrorHelperService errorHelperService) {
        super(objectMapper, errorHelperService);
    }
}
