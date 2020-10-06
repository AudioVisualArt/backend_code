package com.clapp.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component

public class Worker {
	private String userId;
	private String mainRol;
	private String description;
	private List<String> briefcase;
	private String profession;
	private String studies;
	private String hvUrl;
	
	
	
	public String getHvUrl() {
		return hvUrl;
	}
	public void setHvUrl(String hvUrl) {
		this.hvUrl = hvUrl;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getStudies() {
		return studies;
	}
	public void setStudies(String studies) {
		this.studies = studies;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMainRol() {
		return mainRol;
	}
	public void setMainRol(String mainRol) {
		this.mainRol = mainRol;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getBriefcase() {
		return briefcase;
	}
	public void setBriefcase(List<String> briefcase) {
		this.briefcase = briefcase;
	}
	
	
}
