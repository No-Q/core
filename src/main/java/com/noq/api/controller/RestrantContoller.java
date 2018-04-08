package com.noq.api.controller;

import com.noq.api.model.request.RestaurantCreateRequest;
import com.noq.api.service.RestaurantService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "/restaurants", description = "Manage Restaurant" )
@RequestMapping(value = "/restaurants")
@Controller
public class RestrantContoller {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestrantContoller.class);

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public String getAllRestaurants() {

        return restaurantService.getAll();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody(required = true) RestaurantCreateRequest request) {
        LOGGER.info("Received restaurant create request: "+request);
        restaurantService.add(request);
    }
}
