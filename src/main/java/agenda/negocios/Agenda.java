package agenda.negocios;

import java.util.List;

import agenda.persistencia.interfaz.DAOAbstractFactory;
import agenda.persistencia.interfaz.LocalidadDAO;
import agenda.persistencia.interfaz.PersonaDAO;
import agenda.persistencia.interfaz.TipoContactoDAO;
import agenda.persistencia.pojos.LocalidadDTO;
import agenda.persistencia.pojos.PersonaDTO;
import agenda.persistencia.pojos.TipoContactoDTO;

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
	
	public void borrarLocalidad(LocalidadDTO localidad) throws Exception{
		localidadDAO.delete(localidad);
	}

	public void modificarLocalidad(LocalidadDTO localidad) {
		localidadDAO.update(localidad);
	}

	public String obtenerDescripcionDeLocalidad(Integer localidad_id) {
		return localidadDAO.selectDescripcion(localidad_id);
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
	
	public String obtenerDescripcionDeTipoDeContacto(Integer tipo_de_contacto_id) {
		return tipoContactoDAO.selectDescripcion(tipo_de_contacto_id);
	}
	
	public List<TipoContactoDTO> obtenerTiposDeContacto() {
		return tipoContactoDAO.readAll();		
	}
	
}