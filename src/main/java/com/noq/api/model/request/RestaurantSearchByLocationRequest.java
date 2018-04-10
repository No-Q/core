package com.noq.api.model.request;

import javax.validation.constraints.NotNull;

public class RestaurantSearchByLocationRequest {
	 @NotNull(message = "Latitude")
	    private double Latitude;
	    @NotNull(message = "Longitude")
	    private double Longitude;
	    @NotNull(message = "Radius in Km")
	    private int Radius;
	    
	    @Override
		public String toString() {
			return "RestaurantSearchByLocationRequest [Latitude=" + Latitude + ", Longitude=" + Longitude + ", Radius="
					+ Radius + "]";
		}
		public double getLatitude() {
			return Latitude;
		}
		public void setLatitude(double latitude) {
			Latitude = latitude;
		}
		public double getLongitude() {
			return Longitude;
		}
		public void setLongitude(double longitude) {
			Longitude = longitude;
		}
		public int getRadius() {
			return Radius;
		}
		public void setRadius(int radius) {
			Radius = radius;
		}
}
