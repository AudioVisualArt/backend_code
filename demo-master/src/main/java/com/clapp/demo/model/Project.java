package com.clapp.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Project {

	String contacto;
	List<String> crew;
	//List<String>locations;
	String proyectName;
	String projectType;
	String description;
	public String getContacto() {
		return contacto;
	}
	public List<String> getCrew() {
		return crew;
	}
	public String getProyectName() {
		return proyectName;
	}
	public String getProjectType() {
		return projectType;
	}
	public String getDescription() {
		return description;
	}
	
	
	
}
