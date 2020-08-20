package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component
public class ScreenPlay extends Item{
	
	String topic;
	int pages;
	String sinopsis;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
}
