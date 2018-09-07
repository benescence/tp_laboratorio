package agenda.presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import agenda.negocios.Agenda;
import agenda.persistencia.Conexion;
import agenda.persistencia.interfaz.LocalidadDAO;
import agenda.persistencia.interfaz.PersonaDAO;
import agenda.persistencia.interfaz.TipoContactoDAO;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.persistencia.mysql.LocalidadDAOSQL;
import agenda.persistencia.mysql.PersonaDAOSQL;
import agenda.persistencia.mysql.TipoContactoDAOSQL;
import agenda.persistencia.pojos.LocalidadDTO;
import agenda.persistencia.pojos.PersonaDTO;
import agenda.persistencia.pojos.TipoContactoDTO;
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
		if(e.getSource() == ventana.getBtnAceptar() && ventana.validarInputs())	{
			String usuario = ventana.getUsuario();
			String password = ventana.getPassword();
			String IP = ventana.getIP();
			String puerto = ventana.getPuerto();
			
			// TEST QUE LA IP Y PUERTO SEAN CORRECTOS
			if (!Conexion.probarConexion(usuario, password, IP, puerto)) {
				JOptionPane.showMessageDialog(null, "La conexion ha fallado.\nPor favor revise los datos de conexion.");
				return;
			}
			
			// Si los datos son correctos y es la primera sesion creo la BD
			if (Conexion.esPrimeraSesion()) {
				Conexion.crearAgendaBD();
				insertarLocalidadesPorDefecto();
				insertarTiposDeContactoPorDefecto();
				insertarContactosPorDefecto();
				//Conexion.crearUsuario(usuario, password);
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
		else if(e.getSource() == ventana.getBtnProbarconexion() && ventana.validarInputs()) {
			if (Conexion.probarConexion("root", "root", ventana.getIP(), ventana.getPuerto()))
				JOptionPane.showMessageDialog(null, "La conexion ha sido exitosa");
			else
				JOptionPane.showMessageDialog(null, "La conexion ha fallado");
		}
	}

	private void insertarLocalidadesPorDefecto() {
		LocalidadDAO dao = new LocalidadDAOSQL();
		dao.insert(new LocalidadDTO(-1, "San Miguel"));
		dao.insert(new LocalidadDTO(-1, "José C. Paz"));
		dao.insert(new LocalidadDTO(-1, "Moreno"));
		dao.insert(new LocalidadDTO(-1, "Muñiz"));
		dao.insert(new LocalidadDTO(-1, "Polvorines"));
	}

	private void insertarTiposDeContactoPorDefecto() {
		TipoContactoDAO dao = new TipoContactoDAOSQL();
		dao.insert(new TipoContactoDTO(-1, "Familia"));
		dao.insert(new TipoContactoDTO(-1, "Amigos"));
		dao.insert(new TipoContactoDTO(-1, "Trabajo"));
		dao.insert(new TipoContactoDTO(-1, "Facultad"));
		dao.insert(new TipoContactoDTO(-1, "Futbol"));
	}
	
	private void insertarContactosPorDefecto() {
		PersonaDAO dao = new PersonaDAOSQL();
		dao.insert(new PersonaDTO(-1, 1, 1, "Carlos", "Caballero", "123445678", "carlos@gmail.com", "Baker Street", "123", "1", "221B", new Date(System.currentTimeMillis())));
		dao.insert(new PersonaDTO(-1, 2, 2, "Jose", "Sanchez", "55555555", "jose@gmail.com", "Calle verdadera", "456", "1", "4B", new Date(System.currentTimeMillis())));
		dao.insert(new PersonaDTO(-1, 3, 3, "Cyntia", "Gutierrez", "666666", "cyntia@yahoo.com", "Calle falsa", "123", "1", "4B", new Date(System.currentTimeMillis())));
		dao.insert(new PersonaDTO(-1, 4, 4, "Homero", "Simpson", "99996678", "homero@yahoo.com", "Avenida siempre viva", "639", "-", "-", new Date(System.currentTimeMillis())));
		dao.insert(new PersonaDTO(-1, 5, 5, "Jorge", "Fernet", "45645645", "jorge@hotmail.com", "Mitre", "2489", "-", "-", new Date(System.currentTimeMillis())));
	}
	
}