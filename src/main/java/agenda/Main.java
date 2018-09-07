package agenda;

import javax.swing.JOptionPane;
import agenda.negocios.Agenda;
import agenda.persistencia.Conexion;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.presentacion.controlador.Controlador;
import agenda.presentacion.controlador.ControladorConfigurar;
import agenda.presentacion.vista.Vista;
import agenda.presentacion.vista.configurar.VentanaConfigurar;
import agenda.util.Propiedades;

public class Main {
	
	public static boolean probarConexion() {
		String IP = Propiedades.recuperar("IP");
		String puerto = Propiedades.recuperar("puerto");
		String usuario = Propiedades.recuperar("usuario");
		String password = Propiedades.recuperar("password");
		return Conexion.probarConexion(usuario, password, IP, puerto);
	}

	public static void main(String[] args) {
		if (Conexion.esPrimeraSesion()) {
			String mensaje = "Felicidades por adquirir la nueva Agenda UNGS.\n"+
					"Acontinuacion debe configurar los datos de conexion para comenzar a utilizarla.\n"+
					"Recomendamos dejar los datos por defecto.";
			
			JOptionPane.showMessageDialog(null, mensaje);
			VentanaConfigurar ventana = new VentanaConfigurar();
			new ControladorConfigurar(ventana);
		}		
		
		else if (!probarConexion()) {
			String mensaje = "No se ha podido conectar con la base de datos.\n"+
					"Por favor revise los datos de conexion.\n";
			
			JOptionPane.showMessageDialog(null, mensaje);
			VentanaConfigurar ventana = new VentanaConfigurar();
			new ControladorConfigurar(ventana);
			return;
		}		

		else {
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());	
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();	
		}
	}
	
}