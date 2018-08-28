package presentacion.vista.util;

public class Validador {

	public static boolean formatoNumero(String texto) {
		return texto.matches("[0-9]+");
	}
	
	public static boolean formatoLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z\\s]+");
	}
	
	public static boolean formatoNumeroLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z0-9\\s]+");
	}
	
	public static boolean formatoMail(String texto) {
		return texto.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+");
	}
	
	public static void main(String[] args) {
		System.out.println(formatoMail("a@a"));
	}
		
}