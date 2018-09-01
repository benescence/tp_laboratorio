import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Propiedades {
	private static final String direccion = "config.properties";

	public static void guardar(String clave, String valor) {
		Properties parametros = new Properties();
		OutputStream salida = null;
		
		try {
			salida = new FileOutputStream(direccion);
			parametros.setProperty(clave, valor);
			parametros.store(salida, null);
			
			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (salida != null) {
					try {
						salida.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
				
			}
	
	}

	public static String recuperar(String clave) {	
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String ret = "";
		
		try {	
			entrada = new FileInputStream(direccion);
			propiedades.load(entrada);
			ret = propiedades.getProperty(clave);
	
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return ret;
	}
	
	public static void main(String[] args) {
		//guardar("prueba1", "Hola mundo en Properties");
		System.out.println(recuperar("prueba1"));
	}
}