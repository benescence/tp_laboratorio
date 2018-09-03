package agenda.presentacion.reportes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import agenda.persistencia.pojos.PersonaDTO;

public class ReporteUtil {
	
	public static List<PersonaVOReporte> obtenerPersonasVOReporte(List<PersonaDTO> personasDTO) {
		List<PersonaVOReporte> lista = new ArrayList<PersonaVOReporte>();
		
		for (PersonaDTO personaDTO : personasDTO) {
			lista.add(new PersonaVOReporte( 
				personaDTO.getNombre(),
				personaDTO.getApellido(),
				personaDTO.getTelefono(),
				personaDTO.getEmail(),
				extraerServidorDeMail(personaDTO)
				));
		}  
		
		return lista;		
	}

	public static List<PersonaVOReporte> ordenarPersonasVOReporte(List<PersonaVOReporte> personas){
		Collections.sort(personas, new Comparator<PersonaVOReporte>() {
    		
    		@Override
    		public int compare(PersonaVOReporte p1, PersonaVOReporte p2) {
    			
    			 // Ordeno por servidor de email
    			int ret = p1.getServidorMail().compareTo(p2.getServidorMail());
    			
    			// Si son iguales ordeno por apellido
    			if (ret == 0)
    				ret = p1.getApellido().compareTo(p2.getApellido());
    			
    			// Si son iguales ordeno por nombre
    			if (ret == 0)
    				ret = p1.getNombre().compareTo(p2.getNombre());
    			
    			return ret;
    		}
    		
    	});
		
		return personas;
	}

	private static String extraerServidorDeMail(PersonaDTO personaDTO) {
		String email = personaDTO.getEmail();
		if (email.equals("-"))
			return "Sin servidor";
		
		String servidor = "";		
		boolean esArroba = false;
		boolean esPunto = false;
		
		for (int i=0; i<email.length(); i++) {
			char caracter = email.charAt(i);
			esArroba = esArroba || (caracter == '@');
			esPunto = esPunto || (caracter == '.');
			
			if (esArroba && !esPunto)
				servidor += caracter;
		}
		
		return servidor;
	}
	
}