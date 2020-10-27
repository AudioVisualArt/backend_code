package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Music extends Item{

	String genre;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
