package agenda.util;

public class Validador {

	public static boolean formatoNumerico(String texto) {
		return texto.matches("[0-9]+");
	}
	
	public static boolean formatoLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z\\s]+");
	}
	
	public static boolean formatoNumeroLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z0-9\\s]+");
	}
	
	public static boolean formatoMail(String texto) {
		return texto.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+") || texto.equals("-");
	}
	
	public static boolean formatoDepartamento(String texto) {
		return (formatoNumeroLetraEspacio(texto) || texto.equals("-") );
	}

	public static boolean formatoPiso(String texto) {
		return (formatoNumerico(texto) || texto.equals("-") );
	}

	public static boolean formatoApellido(String texto) {
		return (formatoLetraEspacio(texto) || texto.equals("-") );
	}

	public static boolean formatoTelefono(String texto) {
		return (formatoNumerico(texto) || texto.equals("-") );
	}

	public static boolean formatoCalle(String texto) {
		return (formatoNumeroLetraEspacio(texto) || texto.equals("-") );
	}

	public static boolean formatoNumero(String texto) {
		return (formatoNumerico(texto) || texto.equals("-") );
	}

	
	public static void main(String[] args) {
		System.out.println(formatoMail("a@asada"));
	}
		
}