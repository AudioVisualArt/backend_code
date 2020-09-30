package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Space {

	String id;
	String scheduleDays;
	String scheduleHours;
	String name;
	String Location;
	int rating;
	String userOwner;
	String description;
	int minimumHours;
	long priceHour;
	
	
	
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
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
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
	public long getPriceHour() {
		return priceHour;
	}
	public void setPriceHour(long priceHour) {
		this.priceHour = priceHour;
	}
	
	
}
