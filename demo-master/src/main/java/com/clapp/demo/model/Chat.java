package com.clapp.demo.model;
import java.util.List;

import org.springframework.stereotype.Component;
public class Chat {
	private String chatId;
	private String usuarioO;
	private String usuarioD;
	private String photoUrlO;
	private String photoUrlD;
	private String nameD;
	private String nameO;
	private String fecha;
	private List<Mensaje> mensajes;
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getUsuarioO() {
		return usuarioO;
	}

	public void setUsuarioO(String usuarioO) {
		this.usuarioO = usuarioO;
	}

	public String getUsuarioD() {
		return usuarioD;
	}

	public void setUsuarioD(String usuarioD) {
		this.usuarioD = usuarioD;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public String getPhotoUrlO() {
		return photoUrlO;
	}
	public void setPhotoUrlO(String photoUrlO) {
		this.photoUrlO = photoUrlO;
	}
	public String getPhotoUrlD() {
		return photoUrlD;
	}
	public void setPhotoUrlD(String photoUrlD) {
		this.photoUrlD = photoUrlD;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNameD() {
		return nameD;
	}
	public void setNameD(String nameD) {
		this.nameD = nameD;
	}
	public String getNameO() {
		return nameO;
	}
	public void setNameO(String nameO) {
		this.nameO = nameO;
	}

	

	
	
	
}
