package com.noq.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.noq.api.dao.AddressDao;
import com.noq.api.dao.TokenDao;
import com.noq.api.dao.UserDao;
import com.noq.api.dto.UserDto;
import com.noq.api.model.Address;
import com.noq.api.model.User;
import com.noq.api.model.UserRole;
import com.noq.api.model.VerificationToken;
import com.noq.api.model.request.UserAddressAddRequest;
import com.noq.api.model.request.UserCreateRequest;
import com.noq.api.response.AddressResponse;
import com.noq.api.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
    UserDao userDao;
	@Autowired
    AddressDao addressDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenDao tokenDao;

	Gson gson = new Gson();

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
	    User user = new User(request.getName(),request.getEmail(),request.getPhone());
	    user.setActive(Boolean.TRUE);
        userDao.save(user);
    }

    public void addAddress(User user, UserAddressAddRequest request) {
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
        User user = userDao.findByEmail(email);
        return user;
    }
    
    public User getUserByPhone(String phone){
    	User user = userDao.findByPhone(phone);
    	return user;
    }

    public User register(UserDto userDto) {
	    User user = new User();
	    user.setName(userDto.getName());
	    user.setPhone(userDto.getPhone());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	    user.setRole(gson.toJson(Arrays.asList(UserRole.USER.name())));
        LOGGER.info("Saving user into database: "+user);
	    userDao.save(user);
	    return user;
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenDao.save(myToken);
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenDao.findByToken(VerificationToken);
    }

    public void update(User user) {
	    userDao.save(user);
    }
}
