package agenda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	public static boolean probarConexion(String usuario, String password, String IP, String puerto) {
		boolean exito = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://"+IP+":"+puerto+"/", usuario, password);
			exito = true;
		} catch (Exception e) {
			System.out.println("Los datos de entrada para la conexion fallaron");
		}
		
		return exito;
	}
	
	// crea la BD si no existe
	public static void crearAgendaBD() {
		String IP = Propiedades.recuperar("IP");
		String puerto = Propiedades.recuperar("puerto");
		String usuario = Propiedades.recuperar("usuario");
		String password = Propiedades.recuperar("password");
		if (!existeAgenda(usuario, password, IP, puerto)) {
			String crearTipo = "CREATE TABLE tipo_contacto (tipo_contacto_id int(3) AUTO_INCREMENT, descripcion varchar(50) NOT NULL, PRIMARY KEY (tipo_contacto_id));";
			String crearLocalidades = "CREATE TABLE localidades (\r\n" + 
					"	localidad_id int(5) AUTO_INCREMENT,\r\n" + 
					"	descripcion varchar(50) NOT NULL,\r\n" + 
					"	PRIMARY KEY (localidad_id));\r\n"; 
			
			String crearPersonas ="CREATE TABLE personas (\r\n" + 
					"	persona_id int(10) AUTO_INCREMENT,\r\n" + 
					"	nombre varchar(45) NOT NULL,\r\n" + 
					"	apellido varchar(45) NOT NULL,\r\n" + 
					"	telefono varchar(20) NOT NULL,\r\n" + 
					"	email VARCHAR(30) NOT NULL,\r\n" + 
					"	calle VARCHAR(30) NOT NULL,\r\n" + 
					"	numero VARCHAR(5) NOT NULL,\r\n" + 
					"	piso VARCHAR(5),\r\n" + 
					"	depto VARCHAR(5),\r\n" + 
					"	cumple DATE NOT NULL,\r\n" + 
					"	localidad_id int(5),\r\n" + 
					"	tipo_contacto_id int(5),\r\n" + 
					"	PRIMARY KEY (persona_id),\r\n" + 
					"	FOREIGN KEY (localidad_id) REFERENCES localidades(localidad_id),\r\n" + 
					"	FOREIGN KEY (tipo_contacto_id) REFERENCES tipo_contacto(tipo_contacto_id)\r\n" + 
					");";
			
			// CREO LA BASE DE DATOS
			ejecutarQuery("CREATE DATABASE agenda;", usuario, password, IP, puerto, "");
			
			// CREO LAS TABLAS
			ejecutarQuery(crearTipo, usuario, password, IP, puerto, "agenda");
			ejecutarQuery(crearLocalidades, usuario, password, IP, puerto, "agenda");
			ejecutarQuery(crearPersonas, usuario, password, IP, puerto, "agenda");
		}
	}
	
	private static boolean existeAgenda(String usuario, String password, String IP, String puerto) {
		boolean exito = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://"+IP+":"+puerto+"/agenda", usuario, password);
			exito = true;
		} catch (Exception e) {
			System.out.println("Aun no se ha creado la BD agenda o los datos de conexion son invalidos");
		}
		
		return exito;
	}
	
	public static boolean esPrimeraSesion() {
		String primeraVez = Propiedades.recuperar("primera_vez");
		return (primeraVez.equals("SI"));
	}

	private static void ejecutarQuery(String query, String usuario, String password, String IP, String puerto, String BD) {
		String drive = "jdbc:mysql://"+IP+":"+puerto+"/"+BD;
		Connection conexion;
	    try {
	        conexion = (Connection) DriverManager.getConnection(drive,usuario,password);
	        PreparedStatement ps = conexion.prepareStatement(query);
	        ps.executeUpdate();
	        ps.close();
	        conexion.close();
	    } catch (SQLException ex) {
	        System.out.println("Ocurrio un error tratando de ejecutar:");
	    	ex.printStackTrace();
	    }
	}

	// no funciona
	public static void crearUsuario(String Pusuario, String Ppassword) {
		String IP = Propiedades.recuperar("IP");
		String puerto = Propiedades.recuperar("puerto");
		String usuario = Propiedades.recuperar("usuario");
		String password = Propiedades.recuperar("password");
		
		ejecutarQuery("CREATE USER '"+Pusuario+"'@'localhost' IDENTIFIED BY '"+Ppassword+"'", usuario, password, IP, puerto, "agenda");
		ejecutarQuery("GRANT ALL PRIVILEGES ON * . * TO 'carlos'@'localhost'", usuario, password, IP, puerto, "agenda");
		ejecutarQuery("FLUSH PRIVILEGES", usuario, password, IP, puerto, "agenda");
	}
	
}