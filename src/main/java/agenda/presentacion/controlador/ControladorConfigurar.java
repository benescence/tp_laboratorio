package agenda.presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import agenda.negocios.Agenda;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.presentacion.vista.Vista;
import agenda.presentacion.vista.configurar.VentanaConfigurar;
import agenda.util.Propiedades;

public class ControladorConfigurar implements ActionListener {
	private VentanaConfigurar ventana;
	
	public ControladorConfigurar(VentanaConfigurar ventana) {
		this.ventana = ventana;
		this.ventana.getBtnAceptar().addActionListener(this);
		this.ventana.getBtnProbarconexion().addActionListener(this);
	}
		
	public void actionPerformed(ActionEvent e) {
		// ACEPTAR
		if(e.getSource() == ventana.getBtnAceptar())	{
			String usuario = ventana.getUsuario();
			String password = ventana.getPassword();
			String IP = ventana.getIP();
			String puerto = ventana.getPuerto();
			
			if (!probarConexion(usuario, password, IP, puerto)) {
				JOptionPane.showMessageDialog(null, "La conexion ha fallado.\nPor favor revise los datos de conexion.");
				return;
			}
			
			Map<String, String> datos = new HashMap<String, String>();
			datos.put("usuario", usuario);
			datos.put("password", password);
			datos.put("IP", IP);
			datos.put("puerto", puerto);
			datos.put("primera_vez", "N");
			Propiedades.guardar(datos);
			
			// INICIO LA APLICACION
			Vista vista = new Vista();
			Agenda modelo = new Agenda(new DAOSQLFactory());
			Controlador controlador = new Controlador(vista, modelo);
			controlador.inicializar();
			ventana.dispose();
		}
		
		// PROBAR CONEXION
		else if(e.getSource() == ventana.getBtnProbarconexion()) {
			if (probarConexion(ventana.getUsuario(), ventana.getPassword(), ventana.getIP(), ventana.getPuerto()))
				JOptionPane.showMessageDialog(null, "La conexion ha sido exitosa");
			else
				JOptionPane.showMessageDialog(null, "La conexion ha fallado");
		}
	}
	
	private boolean probarConexion(String usuario, String password, String IP, String puerto) {
		boolean exito = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection("jdbc:mysql://"+IP+":"+puerto+"/agenda", usuario, password);
			exito = true;
		} catch (Exception e) {
			System.out.println("Los datos de entrada para la conexion fallaron");
		}
		
		return exito;
	}
}