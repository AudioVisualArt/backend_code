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
	private String ownerId;
	private String executive_summary;
	private String main_file;
	
	
	
	public String getMain_file() {
		return main_file;
	}
	public void setMain_file(String main_file) {
		this.main_file = main_file;
	}
	public void setExecutive_summary(String executive_summary) {
		this.executive_summary = executive_summary;
	}
	public String getExecutive_summary() {
		return executive_summary;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public void setCrew(List<String> crew) {
		this.crew = crew;
	}
	public void setProyectName(String proyectName) {
		this.proyectName = proyectName;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
