package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Event {
	
	private String adresss;
	private String date;
	private String description;
	private String nameEvent;
	private String kindOfEvent;
	private String id;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdresss() {
		return adresss;
	}
	public void setAdresss(String adresss) {
		this.adresss = adresss;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNameEvent() {
		return nameEvent;
	}
	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}
	public String getKindOfEvent() {
		return kindOfEvent;
	}
	public void setKindOfEvent(String kindOfEvent) {
		this.kindOfEvent = kindOfEvent;
	}
	
	

}
