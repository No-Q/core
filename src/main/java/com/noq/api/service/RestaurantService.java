package com.noq.api.service;

import java.util.*;

import com.google.gson.Gson;
import com.noq.api.model.request.DayAvailabilityRequest;
import com.noq.api.model.request.RestaurantCreateRequest;
import com.noq.api.model.response.NextAvailable;
import com.noq.api.model.response.RestaurantListResponse;
import com.noq.dependencies.db.dao.RestaurantAvailabilityDao;
import com.noq.dependencies.db.dao.RestaurantDao;
import com.noq.dependencies.db.model.RestaurantAvailability;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noq.dependencies.db.dao.AddressDao;
import com.noq.dependencies.db.model.Address;
import com.noq.dependencies.db.model.Restaurant;
import com.noq.dependencies.search.DistanceCalculator;
import com.noq.dependencies.search.QuadKeyUtil;

@Service
public class RestaurantService {

	@Autowired 
	AddressDao addressDao;
	@Autowired
    RestaurantDao restaurantDao;
	@Autowired
    RestaurantAvailabilityDao restaurantAvailabilityDao;

	private final Gson gson = new Gson();

	 private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);
	 
	 public String GetNearbyRestaurants(double lat, double longi, double rangeInKm)
	 {
		 String key = QuadKeyUtil.LatLongToQuadKey(13, lat, longi);
		 // get restaurants based on quad key 
		 List<Address> addresses = (List<Address>)addressDao.findByQuadKey(key);
		 List<String> nearByRestaurants = new ArrayList<String>();
		 for(Address address : addresses)
		 {
			 double distance = DistanceCalculator.distance(lat, longi,
					 address.getLat(), address.getLon(), "K");
			 if(distance <= rangeInKm)
			 {
				 nearByRestaurants.add(address.getRestaurant().getName());
			 }
		 }
		 return gson.toJson(nearByRestaurants);
	 }

    public String getAll() {
        Iterable<Restaurant> restaurantList = restaurantDao.findByActive(Boolean.TRUE);
        List<RestaurantListResponse> listRestaurantResponses = new LinkedList<>();
        for(Restaurant restaurant:restaurantList){
            RestaurantListResponse res = new RestaurantListResponse(restaurant.getName(),
                    restaurant.getCostPerPerson(),restaurant.getLandmark(),
                    restaurant.getVegOnly(),restaurant.getCompany(),restaurant.getType(),
                    restaurant.getEmail(),restaurant.getPhone());
            listRestaurantResponses.add(res);
        }
        return gson.toJson(listRestaurantResponses);
    }

    public void add(RestaurantCreateRequest request) {
	     Restaurant restaurant = new Restaurant(request.getName(),request.getEmail(),
                 request.getPhone(),request.getVegOnly());
	     restaurant.setActive(Boolean.TRUE);
	     restaurantDao.save(restaurant);

        Address address = new Address(request.getAddress(), restaurant);
        address.setActive(Boolean.TRUE);
        addressDao.save(address);
	 }

    public void addAvailability(long id, List<DayAvailabilityRequest> request) {

        Optional<Restaurant> restaurantOptional = restaurantDao.findById(id);
	     if(!restaurantOptional.isPresent()){
	         LOGGER.error("Restaurant with given id is not present");
         }else {
	         Restaurant restaurant = restaurantOptional.get();
             LOGGER.info("Updating availability for restaurant"+ restaurant.getName());
             for (DayAvailabilityRequest dayAvailability : request) {
                 int day = dayAvailability.getDayOfWeek().getValue();
                 for(int hour=1;hour<25;hour++){
                     updateAvailability(restaurant,day,hour, Boolean.FALSE);
                 }
                 for (int hour = dayAvailability.getOpenHour(); hour <= dayAvailability.getCloseHour(); hour++) {
                     updateAvailability(restaurant,day,hour, Boolean.TRUE);
                 }
                 for (int hour = dayAvailability.getBreakStartHour(); hour <= dayAvailability.getBreakEndHour(); hour++) {
                     updateAvailability(restaurant,day,hour, Boolean.FALSE);
                 }
             }
         }
    }

    public void updateAvailability(Restaurant restaurant, int day, int hour, Boolean available) {

        RestaurantAvailability restaurantAvailability =
                restaurantAvailabilityDao.findByRestaurantAndDayOfWeekAndHour(restaurant,day,hour);
        if(restaurantAvailability == null){
            restaurantAvailability =  new RestaurantAvailability(restaurant,day,hour, available);
        }else{
            restaurantAvailability.setHourOfOperationAvailable(available);
        }
        restaurantAvailability.setActive(Boolean.TRUE);
        restaurantAvailabilityDao.save(restaurantAvailability);
    }

    /**
     * get all available restaurant for the given day and hour
     * @param dayOfWeek
     * @param hourOfDay
     * @return
     */
    public String getByAvailability(int dayOfWeek, int hourOfDay) {
        LOGGER.info("Getting all available restaurant for day of week:"+dayOfWeek+" hour of day:"+hourOfDay);
        List<RestaurantListResponse> responses = new ArrayList<>();
        Iterable<Restaurant> restaurantList = restaurantDao.findByActive(Boolean.TRUE);

        for(Restaurant restaurant:restaurantList){

            NextAvailable nextAvailable = null;
            RestaurantAvailability availability =
                    restaurantAvailabilityDao.findByRestaurantAndDayOfWeekAndHour(restaurant,dayOfWeek,hourOfDay);
            Boolean available =
                    availability.getHourOfOperationAvailable() == null ? Boolean.FALSE : availability.getHourOfOperationAvailable();
            if(! available || !restaurant.getAvailable()){
                    nextAvailable = getNextAvailable(restaurant.getId(),dayOfWeek,hourOfDay);
            }
            RestaurantListResponse response = new RestaurantListResponse(
                    restaurant.getName(),restaurant.getCostPerPerson(),restaurant.getLandmark(),restaurant.getVegOnly(),
                    restaurant.getCompany(),restaurant.getType(),available,nextAvailable);

            responses.add(response);
        }
        return gson.toJson(responses);
    }

    public NextAvailable getNextAvailable(long restaurantId, Integer dayOfWeek, Integer hourOfDay) {
        NextAvailable nextAvailable = new NextAvailable();

        Optional<Restaurant> restaurantOptional = restaurantDao.findById(restaurantId);
        if(!restaurantOptional.isPresent()){
            LOGGER.error("Restaurant with given id is not present");
        }else {
            Integer nextHour = -1;
            int nextDay = dayOfWeek;
            Restaurant restaurant = restaurantOptional.get();
            nextHour = restaurantAvailabilityDao.findNextHOAvailableHour(restaurant.getId(),dayOfWeek,hourOfDay);
            if(nextHour == null){
                nextDay = (dayOfWeek+1)%8;
                nextHour = restaurantAvailabilityDao.findNextHOAvailableHour(restaurant.getId(),nextDay,hourOfDay);
            }
            if(nextHour != null) {
                nextAvailable = new NextAvailable(nextDay, nextHour);
            }
        }
        return nextAvailable;
    }

    /**
     * sets availability of all restaurant for the current hour and day
     * @param dayOfWeek
     * @param hourOfDay
     */
    public void setRestaurantAvailability(int dayOfWeek, int hourOfDay) {
        Iterable<Restaurant> restaurants = restaurantDao.findByActive(Boolean.TRUE);
        for(Restaurant restaurant : restaurants){
            Boolean available = Boolean.FALSE;
            RestaurantAvailability restaurantAvailability =
                    restaurantAvailabilityDao.findByRestaurantAndDayOfWeekAndHour(restaurant,dayOfWeek,hourOfDay);

            if(restaurantAvailability != null && restaurantAvailability.getHourOfOperationAvailable() != null
                    && restaurantAvailability.getHourOfOperationAvailable()
                    && restaurantAvailability.getPartnerAvailable() !=null && restaurantAvailability.getPartnerAvailable()){

                available = Boolean.TRUE;
            }
            restaurant.setAvailable(available);

            restaurantDao.save(restaurant);
        }
    }
}
