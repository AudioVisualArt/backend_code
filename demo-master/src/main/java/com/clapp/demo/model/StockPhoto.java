package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class StockPhoto extends Item{
	
	String photoType;
	double width;
	double heigth;
	
	
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public double getHeigth() {
		return heigth;
	}
	public void setHeigth(float heigth) {
		this.heigth = heigth;
	}

	
	
}
