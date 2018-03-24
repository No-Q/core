package com.noq.api.controller;

import com.noq.api.dto.UserDto;
import com.noq.api.service.AuthService;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Api(value = "/user", description = "Manages user authentication and registration" )
@RequestMapping(value = "/user")
@Controller
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@RequestBody(required = true) @Valid UserDto userDto) {
        LOGGER.info("Received registration request:"+userDto);
        try{
            authService.registerUser(userDto);
        }catch (Exception e){
            throw new ValidationException(e);
        }

    }
}
