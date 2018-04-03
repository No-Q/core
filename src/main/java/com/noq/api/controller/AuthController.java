package com.noq.api.controller;

import com.noq.api.dto.UserDto;
import com.noq.api.service.AuthService;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Api(description = "Manages user authentication and registration" )
@Controller
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@RequestBody(required = true) @Valid UserDto userDto,
                             WebRequest request) {
        LOGGER.info("Received registration request:"+userDto);
        try{
            authService.registerUser(userDto,request);
        }catch (Exception e){
            throw new ValidationException(e);
        }

    }

    @RequestMapping(value = "/config/confirm", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String confirmRegistration
            (WebRequest request,@RequestParam("token") String token) {

        LOGGER.info("Received config confirmation request with token:"+token);
        authService.confirmEmail(request,token);
        return "redirect:/login";
    }
}
