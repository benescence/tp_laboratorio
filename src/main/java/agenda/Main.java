package agenda;


import agenda.negocios.Agenda;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.presentacion.controlador.Controlador;
import agenda.presentacion.vista.Vista;

public class Main {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());	
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
	}
}