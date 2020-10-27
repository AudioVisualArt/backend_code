package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Prop extends Item{
	String theme;
	String type;
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
