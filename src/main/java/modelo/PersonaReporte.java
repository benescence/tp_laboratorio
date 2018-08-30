package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;

public class PersonaReporte {

	private String nombre, telefono;
	private String email;
	private String servidorMail;
	private Date cumple;

	public PersonaReporte(String nombre, String telefono, String email, String servidorMail, Date date) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.servidorMail = servidorMail;
		this.cumple = date;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public  String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServidorMail() {
		return servidorMail;
	}

	public void setServidorMail(String servidorMail) {
		this.servidorMail = servidorMail;
	}

	public Date getCumple() {
		return cumple;
	}

	public void setCumple(Date cumple) {
		this.cumple = cumple;
	}


	
	
}
