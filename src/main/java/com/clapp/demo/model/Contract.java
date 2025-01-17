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
    String projectId;
    String id;
    double latitud;
    double longitud;
    boolean acceptedBidder;
    boolean acceptedApplicant;    
    List<User> useraplicando;
    List<User> userDenegado;
    boolean publico;

    public boolean isAcceptedBidder() {
        return acceptedBidder;
    }
    

    public List<User> getUseraplicando() {
        return useraplicando;
    }

    public void setUseraplicando(List<User> useraplicando) {
        this.useraplicando = useraplicando;
    }

    public List<User> getUserDenegado() {
        return userDenegado;
    }

    public void setUserDenegado(List<User> userDenegado) {
        this.userDenegado = userDenegado;
    }

    public void setAcceptedBidder(boolean acceptedBidder) {
        this.acceptedBidder = acceptedBidder;
    }

    public boolean isAcceptedApplicant() {
        return acceptedApplicant;
    }

    public void setAcceptedApplicant(boolean acceptedApplicant) {
        this.acceptedApplicant = acceptedApplicant;
    }

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

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


	public boolean isPublico() {
		return publico;
	}


	public void setPublico(boolean publico) {
		this.publico = publico;
	}

}
