package presentacion.vista.contacto;

import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import javax.swing.JComboBox;

public class VentanaContactoAgregar extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JPanel contenedor;
	private JCalendar inFecha;
	private JTextField inEmail, inCalle, inDepto, inNumero, inPiso, inTelefono, inNombre;
	private JButton btnAgregarPersona, btnAgregarLocalidad, btnAgregarTipoContacto;
	private JComboBox<LocalidadDTO> inLocalidad;
	private JComboBox <TipoContactoDTO> inTipoContacto;
	
	public VentanaContactoAgregar(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 500);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);

		// Medidas de la ventana
		int columna1 = 10;
		int columna2 = 140;
		int columna3 = 350;
		
		int fila1 = 10;
		int fila2 = 40;
		int fila3 = 70;
		int fila4 = 100;
		int fila5 = 130;
		int fila6 = 160;
		int fila7 = 190;
		int fila8 = 220;
		int fila9 = 430;
		
		int anchoLabelGrande = 120;
		int anchoLabelChico = 50;
		int anchoCajaGrande = 350;
		int anchoCajaChico = 100;
		int alto = 20;

		// Las etiquetas
		JLabel lblNombre = new JLabel("Nombre y apellido");
		JLabel lblTelefono = new JLabel("Teléfono");
		JLabel lblMail = new JLabel("E- Mail");
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		JLabel lblTipo = new JLabel("Tipo de contacto");		
		JLabel lblCalle = new JLabel("Calle");
		JLabel lblNumero = new JLabel("Número");
		JLabel lblPiso = new JLabel("Piso");
		JLabel lblDepto = new JLabel("Depto");
		JLabel lblLocalidad = new JLabel("Localidad");
		
		lblNombre.setBounds(   columna1, fila1, anchoLabelGrande, alto);
		lblTelefono.setBounds( columna1, fila2, anchoLabelGrande, alto);
		lblMail.setBounds(     columna1, fila3, anchoLabelGrande, alto);
		lblFecha.setBounds(    columna1, fila8, anchoLabelGrande, alto);
		lblTipo.setBounds(     columna1, fila4, anchoLabelGrande, alto);
		lblCalle.setBounds(    columna1, fila6, anchoLabelGrande, alto);
		lblNumero.setBounds(   columna1, fila7, anchoLabelChico, alto);
		lblPiso.setBounds(          180, fila7, anchoLabelChico, alto);
		lblDepto.setBounds(         350, fila7, anchoLabelChico, alto);
		lblLocalidad.setBounds(columna1, fila5, anchoLabelGrande, alto);

		contenedor.add(lblNombre);
		contenedor.add(lblTelefono);
		contenedor.add(lblMail);
		contenedor.add(lblFecha);
		contenedor.add(lblTipo);
		contenedor.add(lblCalle);
		contenedor.add(lblNumero);
		contenedor.add(lblPiso);
		contenedor.add(lblDepto);
		contenedor.add(lblLocalidad);
		
		// Las cajas de texto
		inEmail = new JTextField();
		inTelefono = new JTextField();
		inFecha = new JCalendar();
		inCalle = new JTextField();
		inDepto = new JTextField();
		inNumero = new JTextField();
		inPiso = new JTextField();
		inNombre = new JTextField();
		
		inNombre.setColumns(10);
		inEmail.setColumns(10);
		inTelefono.setColumns(10);
		inCalle.setColumns(10);
		inDepto.setColumns(10);
		inNumero.setColumns(10);
		inPiso.setColumns(10);

		inNombre.setBounds(  columna2, fila1, anchoCajaGrande, alto);
		inTelefono.setBounds(columna2, fila2, anchoCajaGrande, alto);
		inEmail.setBounds(   columna2, fila3, anchoCajaGrande, alto);
		inFecha.setBounds(   columna2, fila8, 200, 200);
		inCalle.setBounds(   columna2, fila6, anchoCajaGrande, alto);
		inNumero.setBounds(  70, fila7, anchoCajaChico, alto);
		inPiso.setBounds(    230, fila7, anchoCajaChico, alto);
		inDepto.setBounds(   390, fila7, anchoCajaChico, alto);

		contenedor.add(inNombre);
		contenedor.add(inEmail);
		contenedor.add(inTelefono);
		contenedor.add(inFecha);
		contenedor.add(inCalle);
		contenedor.add(inNumero );
		contenedor.add(inDepto);
		contenedor.add(inPiso);
		
		// Los desplegables
		cargarLocalidades();
		cargarTiposDeContacto();

		inTipoContacto.setBounds(columna2, fila4, 200, alto);
		contenedor.add(inTipoContacto);
		
		// Los botones
		btnAgregarPersona = new JButton("Agregar nuevo contacto");
		btnAgregarTipoContacto = new JButton("Administrar");
		btnAgregarLocalidad = new JButton("Administrar");
		
		
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarTipoContacto.addActionListener(this.controlador);
		
		btnAgregarPersona.setBounds(140, fila9, 200, alto);
		btnAgregarLocalidad.setBounds(columna3, fila5, 140, alto);
		btnAgregarTipoContacto.setBounds(columna3, fila4, 140, alto);

		contenedor.add(btnAgregarPersona);
		contenedor.add(btnAgregarTipoContacto);
		contenedor.add(btnAgregarLocalidad);
		
		this.setVisible(true);
	}
	
	public void cargarLocalidades() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		JComboBox<LocalidadDTO> lista = new JComboBox<LocalidadDTO>();

		for (LocalidadDTO localidad : localidades)
			lista.addItem(localidad);
		
		if (inLocalidad != null)
			contenedor.remove(inLocalidad);
		inLocalidad = lista;
		inLocalidad.setBounds(140, 130, 200, 20);
		contenedor.add(inLocalidad);
	}
	
	public void cargarTiposDeContacto() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<TipoContactoDTO> tipos = agenda.obtenerTiposDeContacto();
		JComboBox<TipoContactoDTO> lista = new JComboBox<TipoContactoDTO>();

		for (TipoContactoDTO tipo: tipos)
			lista.addItem(tipo);
		
		inTipoContacto = lista;
	}
	
	// GETTERS DE DATOS Y BOTONES
	public LocalidadDTO getLocalidad() {
		return (LocalidadDTO)inLocalidad.getSelectedItem();
	}
	
	public TipoContactoDTO getTipoContacto() {
		return (TipoContactoDTO)inTipoContacto.getSelectedItem();
	}
	
	public String getNombre() {
		return inNombre.getText();
	}

	public String getTelefono() {
		return inTelefono.getText();
	}
	
	public String getMail() {
		return inEmail.getText();
	}
    
	public String getCalle() {
		return inCalle.getText();
	}

	public String getNumero() {
		return inNumero.getText();
	}
	
	public String getPiso() {
		return inPiso.getText();
	}
	
	public String getDepto() {
		return inDepto.getText();
	}
	
	public Date getFecha() {
		java.util.Date utilDate = inFecha.getDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}
	
	public JButton getBtnAgregarTipoContacto() {
		return btnAgregarTipoContacto;
	}

}