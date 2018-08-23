package modelo;

import java.util.List;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	private PersonaDAO personaDAO;	
	private LocalidadDAO localidadDAO;
	private TipoContactoDAO tipoContactoDAO;
	
	public Agenda(DAOAbstractFactory metodo_persistencia) {
		personaDAO = metodo_persistencia.crearPersonaDAO();
		localidadDAO = metodo_persistencia.crearLocalidadDAO();
		tipoContactoDAO = metodo_persistencia.crearTipoContactoDAO();
	}
	
	
	// ABM PERSONAS
	public void agregarPersona(PersonaDTO persona) {
		personaDAO.insert(persona);
	}
	
	public void borrarPersona(PersonaDTO persona) {
		personaDAO.delete(persona);
	}
	
	public void modificarPersona(PersonaDTO persona) {
		personaDAO.update(persona);
	}
	
	public List<PersonaDTO> obtenerPersonas() {
		return personaDAO.readAll();		
	}

	
	// ABM LOCALIDADES
	public void agregarLocalidad(LocalidadDTO localidad) {
		localidadDAO.insert(localidad);
	}
	
	public void borrarLocalidad(LocalidadDTO localidad) {
		localidadDAO.delete(localidad);
	}

	public void modificarLocalidad(LocalidadDTO localidad) {
		localidadDAO.update(localidad);
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return localidadDAO.readAll();		
	}
	
	
	// ABM TIPOS DE CONTACTO
	public void agregarTipoDeContacto(TipoContactoDTO tipo) {
		tipoContactoDAO.insert(tipo);
	}
	
	public void borrarTipoDeContacto(TipoContactoDTO tipo) {
		tipoContactoDAO.delete(tipo);
	}

	public void modificarTipoDeContacto(TipoContactoDTO tipo) {
		tipoContactoDAO.update(tipo);
	}
	
	public List<TipoContactoDTO> obtenerTiposDeContacto() {
		return tipoContactoDAO.readAll();		
	}
	
}