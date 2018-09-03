package agenda;

import javax.swing.JOptionPane;
import agenda.negocios.Agenda;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.presentacion.controlador.Controlador;
import agenda.presentacion.controlador.ControladorConfigurar;
import agenda.presentacion.vista.Vista;
import agenda.presentacion.vista.configurar.VentanaConfigurar;
import agenda.util.Propiedades;

public class Main {
	
	public static boolean esPrimeraSesion() {
		String primeraVez = Propiedades.recuperar("primera_vez");
		return (primeraVez.equals("SI"));
	}

	public static void main(String[] args) {
				
		if (esPrimeraSesion()) {
			String mensaje = "Felicidades por adquirir la nueva Agenda UNGS.\n"+
					"Acontinuacion debe configurar los datos de conexion para comenzar a utilizarla.\n"+
					"Recomendamos dejar los datos por defecto.";
			
			JOptionPane.showMessageDialog(null, mensaje);
			VentanaConfigurar ventana = new VentanaConfigurar();
			new ControladorConfigurar(ventana);
		}		
		else {
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());	
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();	
		}
	}
	
}