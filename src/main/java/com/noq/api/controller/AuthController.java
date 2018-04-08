package com.noq.api.controller;

import com.noq.api.model.request.UserRegistrationRequest;
import com.noq.api.service.AuthService;

import com.noq.dependencies.db.model.enums.VerificationTokenType;
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
    public void registerUser(@RequestBody(required = true) @Valid UserRegistrationRequest userDto,
                             WebRequest request) {
        LOGGER.info("Received registration request:"+userDto);
        try{
            authService.registerUser(userDto,request);
        }catch (Exception e){
            throw new ValidationException(e);
        }

    }

    @RequestMapping(value = "/email/verify", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String verifyEmail
            (WebRequest request,@RequestParam("token") String token) {

        LOGGER.info("Received email confirmation request with token:"+token);
        authService.verifyToken(request,token,VerificationTokenType.EMAIL);
        return "redirect:/login";
    }

    @RequestMapping(value = "/phone/verify", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String verifyPhoneNumber
            (WebRequest request,@RequestParam("otp") String otp) {

        LOGGER.info("Received phone verification request with otp:"+otp);
        authService.verifyToken(request,otp,VerificationTokenType.SMS);
        return "redirect:/login";
    }
}
