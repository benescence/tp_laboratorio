package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;

public class PersonaReporte {

	private String nombre, telefono, email,servidorMail,cumple;

	public PersonaReporte(String nombre, String telefono, String email, String servidorMail, String cumple) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.servidorMail = servidorMail;
		this.cumple = cumple;
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

	public String getEmail() {
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

	public String getCumple() {
		return cumple;
	}

	public void setCumple(String cumple) {
		this.cumple = cumple;
	}
	
public static List <PersonaReporte> personasServidor(List<PersonaDTO> personasDTO){
		
		List <PersonaReporte> lista = new ArrayList<PersonaReporte>();
		for (PersonaDTO personaDTO : personasDTO) {
			lista.add(new PersonaReporte( 
				personaDTO.getNombre(), 
				personaDTO.getTelefono(),
				personaDTO.getEmail(),
				null,
				null));	
				
					
		}  
		
		return lista;
		
	}
	
	
	
}
