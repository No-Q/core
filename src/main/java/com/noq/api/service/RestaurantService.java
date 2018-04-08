package com.noq.api.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.noq.api.model.request.RestaurantCreateRequest;
import com.noq.dependencies.db.dao.RestaurantDao;
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

	private final Gson gson = new Gson();

	 private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);
	 
	 public String GetNearbyRestaurants(String lat, String longi, double rangeInKm)
	 {
		 String key = QuadKeyUtil.LatLongToQuadKey(15, Double.parseDouble(lat), Double.parseDouble(longi));
		 // get restaurants based on quad key 
		 List<Address> addresses = (List<Address>)addressDao.findByQuadKey(key);
		 List<Restaurant> nearByRestaurants = new ArrayList<Restaurant>();
		 for(Address address : addresses)
		 {
			 double distance = DistanceCalculator.distance(Double.parseDouble(lat), Double.parseDouble(longi),
					 address.getLat(), address.getLon(), "K");
			 if(distance <= rangeInKm)
			 {
				 nearByRestaurants.add(address.getRestaurant());
			 }
		 }
		 return gson.toJson(nearByRestaurants);
	 }

    public String getAll() {
        return gson.toJson(restaurantDao.findAll());
    }

    public void add(RestaurantCreateRequest request) {
	     Restaurant restaurant = new Restaurant(request.getName(),request.getEmail(),
                 request.getPhone(),request.getVegOnly());
        restaurantDao.save(restaurant);

        Address address = new Address(request.getAddress(), restaurant);
        addressDao.save(address);
	 }
}
