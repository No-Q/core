package com.noq.api.exception.handler;

import com.noq.api.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleBindException
            (BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("400 Status Code", ex);
        BindingResult result = ex.getBindingResult();
        GenericResponse bodyOfResponse =
                new GenericResponse(result.getFieldErrors(), result.getGlobalErrors());

        return handleExceptionInternal(
                ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
