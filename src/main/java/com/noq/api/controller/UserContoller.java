package com.noq.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.ValidationException;
import com.noq.dependencies.db.model.User;
import com.noq.api.model.request.UserAddressAddRequest;
import com.noq.api.model.request.UserCreateRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.noq.api.service.UserService;

@Api(value = "/users", description = "Manage users" )
@RequestMapping(value = "/users")
@Controller
public class UserContoller {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserContoller.class);

    @Autowired
	private UserService userService;

	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public String getAllUsers() {
		
		return userService.getAllUserList();
	}

	@RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody(required = true) UserCreateRequest request) {
        userService.add(request);
    }

    @RequestMapping(value = "/{id}/address",method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addAddress(@PathVariable("id") long userId,
            @RequestBody(required = true) UserAddressAddRequest request) {
	    User user = userService.getUser(userId);
	    if(user == null){
	        throw new ValidationException("User with given id doesn't exists");
        }else {
            userService.addAddress(user, request);
        }
    }

    @RequestMapping(value = "/{id}/address",method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public String getAddress(@PathVariable("id") long userId) {
        LOGGER.info("Received get all address request for user id:"+userId);
	    String response = null;
        User user = userService.getUser(userId);
        if(user == null){
            throw new ValidationException("User with given id doesn't exists");
        }else {
            response = userService.getAllAddresses(user);
        }
        return response;
    }

}
