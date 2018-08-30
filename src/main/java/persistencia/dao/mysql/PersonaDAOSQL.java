package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(nombre, apellido, telefono, email, calle, numero, piso, depto, localidad_id, cumple, tipo_contacto_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE persona_id = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE personas\r\n" + 
			"SET\r\n" + 
			"nombre = ?,\r\n" + 
			"apellido = ?,\r\n" + 
			"telefono = ?,\r\n" + 
			"email = ?,\r\n" + 
			"calle = ?,\r\n" + 
			"numero = ?,\r\n" + 
			"piso = ?,\r\n" + 
			"depto = ?,\r\n" + 
			
			"localidad_id = ?,\r\n" + 
			"tipo_contacto_id = ?\r\n" + 
			"WHERE persona_id = ?;\r\n" + 
			"";
	

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setString(5, persona.getCalle());
			statement.setString(6, persona.getNumero());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getDepto());
			statement.setInt(9, persona.getLocalidad_id());
			statement.setDate(10, persona.getCumple());
			statement.setInt(11, persona.getTipo_contacto_id());
			
			if(statement.executeUpdate() > 0)
				return true;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void update(PersonaDTO persona) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setString(5, persona.getCalle());
			statement.setString(6, persona.getNumero());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getDepto());
			statement.setInt(9, persona.getLocalidad_id());
			statement.setInt(10, persona.getTipo_contacto_id());
			statement.setInt(11, persona.getPersona_id());
			
			statement.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getPersona_id()));
			chequeoUpdate = statement.executeUpdate();
			
			if(chequeoUpdate > 0)
				return true;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		
		try	{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
				personas.add(new PersonaDTO(
						resultSet.getInt("persona_id"),
						resultSet.getInt("localidad_id"),
						resultSet.getInt("tipo_contacto_id"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getString("telefono"),
						resultSet.getString("email"),
						resultSet.getString("calle"),
						resultSet.getString("numero"),
						resultSet.getString("piso"),
						resultSet.getString("depto"),
						resultSet.getDate("cumple")
						));
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}

		return personas;
	}
	
}