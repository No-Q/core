package com.noq.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.noq.api.model.request.UserRegistrationRequest;
import com.noq.dependencies.db.dao.AddressDao;
import com.noq.dependencies.db.dao.UserDao;
import com.noq.dependencies.db.model.Address;
import com.noq.dependencies.db.model.User;
import com.noq.dependencies.db.model.enums.UserRole;
import com.noq.api.model.request.AddressAddRequest;
import com.noq.api.model.request.UserCreateRequest;
import com.noq.api.model.response.AddressResponse;
import com.noq.api.model.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import javax.validation.ValidationException;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RestaurantService restaurantService;

    private final Gson gson = new Gson();

    public User getUser(long id){
        Optional<User> optional = userDao.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }
        return user;
    }

    public String getAllUserList() {
        List<User> users = (List<User>) userDao.findAll();
        List<UserResponse> response = new ArrayList<>();
        for(User user : users){
            UserResponse userResponse = new UserResponse(user.getId(),user.getName(),user.getEmail(),
                    user.getPhone(),user.getReferralCode(),user.getRating());
            response.add(userResponse);
        }
        return gson.toJson(response);
    }

    public void add(UserCreateRequest request) {
        if (getUserByPhone(request.getPhone())!=null) {
            throw new ValidationException("There is already an account with phone number: "
                    +  request.getPhone());
        }else {
            User user = new User(request.getName(), request.getEmail(), request.getPhone());
            user.setActive(Boolean.TRUE);
            userDao.save(user);
        }
    }

    public void addAddress(User user, AddressAddRequest request) {
        Address address = new Address(request.getLine1(),request.getLine2(),
                request.getCity(),request.getState(),request.getZip(),request.getLat(),
                request.getLon(),user);
        address.setActive(Boolean.TRUE);
        addressDao.save(address);
    }

    public String getAllAddresses(User user) {
        List<Address> addresses = addressDao.findByUser(user);
        List<AddressResponse> addressResponses = new ArrayList<>();
        for(Address address : addresses){
            LOGGER.info("Adding address:"+address.toString());
            AddressResponse response = new AddressResponse(address.getLine1(),address.getLine2(),
                    address.getCity(),address.getState(),address.getZip(),address.getLat(),
                    address.getLon(),address.getUser().getId());
            addressResponses.add(response);
        }
        return gson.toJson(addressResponses);
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User getUserByPhone(String phone){
        return userDao.findByPhone(phone);
    }

    public User register(UserRegistrationRequest userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        LOGGER.info("Saving user into database: "+user);
        userDao.save(user);
        return user;
    }

    public void update(User user) {
        userDao.save(user);
    }
}
