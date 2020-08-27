package com.clapp.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Contract {

	String city;
	List<String> desiredSkills;
	String jobPosition;
	float payment;
	int workHours;
	String userApplicantId;
	String userBidderId;
	String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getDesiredSkills() {
		return desiredSkills;
	}
	public void setDesiredSkills(List<String> desiredSkills) {
		this.desiredSkills = desiredSkills;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public int getWorkHours() {
		return workHours;
	}
	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}
	public String getUserApplicantId() {
		return userApplicantId;
	}
	public void setUserApplicantId(String userApplicantId) {
		this.userApplicantId = userApplicantId;
	}
	public String getUserBidderId() {
		return userBidderId;
	}
	public void setUserBidderId(String userBidderId) {
		this.userBidderId = userBidderId;
	}
	
	
}