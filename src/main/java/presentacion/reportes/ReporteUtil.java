package presentacion.reportes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import persistencia.dto.PersonaDTO;

public class ReporteUtil {
	
	public static List<PersonaVOReporte> obtenerPersonasVOReporte(List<PersonaDTO> personasDTO) {

		List<PersonaVOReporte> lista = new ArrayList<PersonaVOReporte>();
		for (PersonaDTO personaDTO : personasDTO) {
			lista.add(new PersonaVOReporte( 
				personaDTO.getPersona_id(),
				personaDTO.getLocalidad_id(),
				personaDTO.getTipo_contacto_id(),
				personaDTO.getNombre(),
				personaDTO.getApellido(),
				personaDTO.getTelefono(),
				personaDTO.getEmail(),
				null,
				null,
				null,
				personaDTO.getCalle(),
				personaDTO.getNumero(),
				personaDTO.getPiso(),
				personaDTO.getDepto(),
				null
				));
					
		}  
		
		return null;		
	}

	public static List<PersonaVOReporte> ordenarPersonasVOReporte(List<PersonaVOReporte> personas){
		Collections.sort(personas, new Comparator<PersonaVOReporte>() {
    		
    		@Override
    		public int compare(PersonaVOReporte p1, PersonaVOReporte p2) {
    			return new Integer (p1.getCumple().compareTo(p2.getCumple()));
    		}
    		
    	});
	
		
		return null;
	}

}