package persistencia;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Propiedades {
	private static final String direccion = "config.properties";

	public static void guardar(Map<String, String> valores) {
		Properties parametros = new Properties();
		OutputStream salida = null;
		
		try {
			salida = new FileOutputStream(direccion);
			
			for (Map.Entry<String, String> entrada: valores.entrySet())
				parametros.setProperty(entrada.getKey(), entrada.getValue());			

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
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("IP", "localhost");
		mapa.put("puerto", "3306");
		mapa.put("usuario", "root");
		mapa.put("password", "root");
		guardar(mapa);
		
		System.out.println(recuperar("IP"));
		System.out.println(recuperar("puerto"));
		System.out.println(recuperar("usuario"));
		System.out.println(recuperar("password"));
	}
	
}