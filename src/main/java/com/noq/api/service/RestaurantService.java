package com.noq.api.service;

import java.util.ArrayList;
import java.util.List;

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
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);
	 
	 public List<Restaurant> GetNearbyRestaurants(String lat, String longi, double rangeInKm)
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
		 return nearByRestaurants;
	 }
}
