package com.noq.api.controller;

import com.noq.api.model.request.DayAvailabilityRequest;
import com.noq.api.model.request.RestaurantCreateRequest;
import com.noq.api.model.request.RestaurantSearchByLocationRequest;
import com.noq.api.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/restaurants", description = "Manage Restaurant" )
@RequestMapping(value = "/restaurants")
@Controller
public class RestrantContoller {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestrantContoller.class);

    @Autowired
    private RestaurantService restaurantService;

    @ApiOperation(value = "get all active restaurant with availability information")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllRestaurants() {

        return restaurantService.getAll();
    }

    @ApiOperation(value = "get all active available restaurant for given day and hour with availability information")
    @RequestMapping(value = "/available",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getByAvailability(
            @RequestParam("day") int dayOfWeek,@RequestParam("hour") int hourOfDay) {

        return restaurantService.getByAvailability(dayOfWeek,hourOfDay);
    }

    @ApiOperation(value = "resets availability of all restaurant for current hour")
    @RequestMapping(value = "/all/available",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void setRestaurantAvailability(
            @RequestParam("day") int dayOfWeek,@RequestParam("hour") int hourOfDay) {
        LOGGER.info("Received set restaurant availability request; day of week:"+dayOfWeek+" hour of day:"+hourOfDay);
        restaurantService.setRestaurantAvailability(dayOfWeek,hourOfDay);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getNearByRestaurants(@RequestBody(required = true) RestaurantSearchByLocationRequest request) {
        LOGGER.info("Received restaurant search request: "+request);

        return restaurantService.GetNearbyRestaurants(request.getLatitude(), request.getLongitude(), request.getRadius());
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addRestaurant(@RequestBody(required = true) RestaurantCreateRequest request) {
        LOGGER.info("Received restaurant create request: "+request);
        restaurantService.add(request);
    }

    @ApiOperation(value="set/reset restaurant availability")
    @RequestMapping(value = "{id}/availability",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addAvailability(
            @PathVariable("id") long id,
            @RequestBody(required = true) List<DayAvailabilityRequest> request){
        LOGGER.info("Received restaurant availability add request: "+request);
        restaurantService.addAvailability(id,request);
    }
}
