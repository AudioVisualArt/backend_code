package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Equipment extends Item{

	String specs;
	String marca;
	String modelo;
	
	
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
