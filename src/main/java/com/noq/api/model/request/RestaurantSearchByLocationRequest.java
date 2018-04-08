package com.noq.api.model.request;

import javax.validation.constraints.NotNull;

public class RestaurantSearchByLocationRequest {
	 @NotNull(message = "Latitude")
	    private String Latitude;
	    @NotNull(message = "Longitude")
	    private String Longitude;
	    @NotNull(message = "Radius in Km")
	    private int Radius;
	    
	    @Override
		public String toString() {
			return "RestaurantSearchByLocationRequest [Latitude=" + Latitude + ", Longitude=" + Longitude + ", Radius="
					+ Radius + "]";
		}
		public String getLatitude() {
			return Latitude;
		}
		public void setLatitude(String latitude) {
			Latitude = latitude;
		}
		public String getLongitude() {
			return Longitude;
		}
		public void setLongitude(String longitude) {
			Longitude = longitude;
		}
		public int getRadius() {
			return Radius;
		}
		public void setRadius(int radius) {
			Radius = radius;
		}
}
