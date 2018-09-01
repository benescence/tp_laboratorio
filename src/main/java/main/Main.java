package main;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.Propiedades;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main {

	public static void main(String[] args) {
		String IP = Propiedades.recuperar("IP");
		String puerto = Propiedades.recuperar("puerto");
		String usuario = Propiedades.recuperar("usuario");
		String password = Propiedades.recuperar("password");
		
		if (IP == null || puerto == null || usuario == null || password == null)
			JOptionPane.showMessageDialog(null, "Antes de usar la Agenda debe setear los datos de conexion con la base de datos");
		else {
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());	
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();			
		}
	}
	
}