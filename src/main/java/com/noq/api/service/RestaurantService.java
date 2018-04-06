package com.noq.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noq.dependencies.db.dao.AddressDao;
import com.noq.dependencies.db.model.Address;

@Service
public class RestaurantService {

	@Autowired 
	AddressDao addressDao;
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
	 
	 public void GetNearbyRestaurants(String lat, String longi, double rangeInKm)
	 {
		 String key = TileSystem.LatLongToQuadKey(15, Double.parseDouble(lat), Double.parseDouble(longi));
		 // get restaurants based on quad key lets say we got 2 restaurants based on key 
		 List<Address> addresses = (List<Address>)addressDao.findAll();
		 List<Address> nearbyAddresses = new ArrayList<Address>();
		 for(Address address : addresses)
		 {
			 double distance = DistanceCalculator.distance(Double.parseDouble(lat), Double.parseDouble(longi),
					 address.getLat(), address.getLon(), "K");
			 System.out.println(distance);
			 if(distance < rangeInKm)
			 {
				 nearbyAddresses.add(address);
			 }
			 
		 }
	 }
}
