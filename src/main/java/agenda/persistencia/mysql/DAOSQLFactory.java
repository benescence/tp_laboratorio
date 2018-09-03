package agenda.persistencia.mysql;

import agenda.persistencia.interfaz.DAOAbstractFactory;
import agenda.persistencia.interfaz.LocalidadDAO;
import agenda.persistencia.interfaz.PersonaDAO;
import agenda.persistencia.interfaz.TipoContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	@Override
	public PersonaDAO crearPersonaDAO() {
		return new PersonaDAOSQL();
	}

	@Override
	public LocalidadDAO crearLocalidadDAO() {
		return new LocalidadDAOSQL();
	}

	@Override
	public TipoContactoDAO crearTipoContactoDAO() {
		return new TipoContactoDAOSQL();
	}

}