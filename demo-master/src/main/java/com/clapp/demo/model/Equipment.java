package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Equipment extends Item{

	String specs;
	String marca;
	String modelo;
	boolean rent;
	boolean sell;
	String tag;
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public boolean isRent() {
		return rent;
	}
	public void setRent(boolean rent) {
		this.rent = rent;
	}
	public boolean isSell() {
		return sell;
	}
	public void setSell(boolean sell) {
		this.sell = sell;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
}
