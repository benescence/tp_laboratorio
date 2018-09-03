package agenda.persistencia.interfaz;

import java.util.List;

import agenda.persistencia.pojos.PersonaDTO;

public interface PersonaDAO {
	
	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona);
	
	public void update(PersonaDTO persona);
	
	public List<PersonaDTO> readAll();
	
}