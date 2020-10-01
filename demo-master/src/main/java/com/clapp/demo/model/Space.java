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
	int minimumHours;
	double priceHour;
	String imageUrl;
	
	
	
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
	public String getlocation() {
		return location;
	}
	public void setlocation(String location) {
		location = location;
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
	public int getMinimumHours() {
		return minimumHours;
	}
	public void setMinimumHours(int minimumHours) {
		this.minimumHours = minimumHours;
	}
	public double getPriceHour() {
		return priceHour;
	}
	public void setPriceHour(double priceHour) {
		this.priceHour = priceHour;
	}
	
	
}
