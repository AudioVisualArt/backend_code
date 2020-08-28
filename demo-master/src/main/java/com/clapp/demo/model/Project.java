package com.clapp.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Project {

	private String contacto;
	private List<String> crew;
	//List<String>locations;
	private String proyectName;
	private String projectType;
	String description;
	private String id;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
