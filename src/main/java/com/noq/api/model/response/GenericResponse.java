package com.noq.api.model.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class GenericResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericResponse.class);

    private String message;
    private String error;

    public GenericResponse(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
        super();
        message = "";
        error = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.message = mapper.writeValueAsString(fieldErrors);
            this.error = mapper.writeValueAsString(globalErrors);
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception while creating response:",e);
        }
    }
}
