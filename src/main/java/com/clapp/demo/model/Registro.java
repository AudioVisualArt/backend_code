package com.clapp.demo.model;

import org.springframework.stereotype.Component;

@Component 
public class Registro {
	String IdUser;

	public String getIdUser() {
		return IdUser;
	}

	public void setIdUser(String idUser) {
		IdUser = idUser;
	}

}
