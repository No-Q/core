package com.noq.api.service;

import com.noq.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    public void registerUser(UserDto userDto) throws ValidationException{

        if (userService.getUserByEmail(userDto.getEmail()) != null) {
            throw new ValidationException("There is an account with that email adress: "
                    +  userDto.getEmail());
        }else{
            userService.register(userDto);
        }
    }

}
