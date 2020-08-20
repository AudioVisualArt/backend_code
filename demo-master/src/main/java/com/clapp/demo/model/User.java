package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class User {
	String id;
	int age;
	String cityResidence;
	String description;
	String email;
	String name;
	int rating;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCityResidence() {
		return cityResidence;
	}
	public void setCityResidence(String cityResidence) {
		this.cityResidence = cityResidence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	
}
