package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Space {

	String id;
	String scheduleDays;
	String scheduleHours;
	String name;
	String location;
	int rating;
	String userOwner;
	String description;
	String minimumHours;
	double priceHour;
	String imageUrl;
	double latitud;
	double longitud;
	
	
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScheduleDays() {
		return scheduleDays;
	}
	public void setScheduleDays(String scheduleDays) {
		this.scheduleDays = scheduleDays;
	}
	public String getScheduleHours() {
		return scheduleHours;
	}
	public void setScheduleHours(String scheduleHours) {
		this.scheduleHours = scheduleHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(String userOwner) {
		this.userOwner = userOwner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMinimumHours() {
		return minimumHours;
	}
	public void setMinimumHours(String minimumHours) {
		this.minimumHours = minimumHours;
	}
	public double getPriceHour() {
		return priceHour;
	}
	public void setPriceHour(double priceHour) {
		this.priceHour = priceHour;
	}
	
	
}
