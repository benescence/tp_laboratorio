package agenda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import agenda.util.Propiedades;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	
	private Conexion() {
		try	{
			String IP = Propiedades.recuperar("IP");
			String puerto = Propiedades.recuperar("puerto");
			String usuario = Propiedades.recuperar("usuario");
			String password = Propiedades.recuperar("password");
			
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://"+IP+":"+puerto+"/agenda", usuario, password);
			log.info("Conexión exitosa");
		}
		
		catch(Exception e) {
			log.error("Conexión fallida", e);
		}
	}
	
	public static Conexion getConexion() {								
		if(instancia == null)
			instancia = new Conexion();
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		}
		
		catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		
		instancia = null;
	}

}