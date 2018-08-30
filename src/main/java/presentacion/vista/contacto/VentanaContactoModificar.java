package presentacion.vista.contacto;

import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.util.Validador;

import javax.swing.JComboBox;

public class VentanaContactoModificar extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JPanel contenedor;
	private JCalendar inFecha;
	private JTextField inEmail, inCalle, inDepto, inNumero, inPiso, inTelefono, inNombre;
	private JButton btnModificarContacto, btnLocalidadABM, btnTipoContactoABM;
	private JComboBox<LocalidadDTO> inLocalidad;
	private JComboBox <TipoContactoDTO> inTipoContacto;
	private PersonaDTO persona;
	
	public VentanaContactoModificar(Controlador controlador, PersonaDTO persona) {
		super();
		this.controlador = controlador;
		this.persona = persona;
		
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
		JLabel lblFecha = new JLabel("Fecha de cumpleaños");
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
		
		inNombre.setText(persona.getNombre());
		inEmail.setText(persona.getEmail());
		inTelefono.setText(persona.getTelefono());
		inFecha.setDate(persona.getCumple());
		inCalle.setText(persona.getCalle());
		inDepto.setText(persona.getDepto());
		inNumero.setText(persona.getNumero());
		inPiso.setText(persona.getPiso());
	
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

		// Los botones
		btnModificarContacto = new JButton("Modificar contacto actual");
		btnTipoContactoABM = new JButton("Administrar");
		btnLocalidadABM = new JButton("Administrar");
		
		btnModificarContacto.addActionListener(this.controlador);
		btnLocalidadABM.addActionListener(this.controlador);
		btnTipoContactoABM.addActionListener(this.controlador);
		
		btnModificarContacto.setBounds(140, fila9, 200, 25);
		btnLocalidadABM.setBounds(columna3, fila5, 140, alto);
		btnTipoContactoABM.setBounds(columna3, fila4, 140, alto);

		contenedor.add(btnModificarContacto);
		contenedor.add(btnTipoContactoABM);
		contenedor.add(btnLocalidadABM);
		
		this.setVisible(true);
	}

	public void cargarLocalidades() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		LocalidadDTO localidadActual = localidades.get(0);
		JComboBox<LocalidadDTO> lista = new JComboBox<LocalidadDTO>();
		lista.setBounds(140, 130, 200, 20);
		
		for (LocalidadDTO localidad : localidades) {
			lista.addItem(localidad);
			if (localidad.getLocalidad_id() == persona.getLocalidad_id())
				localidadActual = localidad;
		}
		
		if (inLocalidad != null)
			contenedor.remove(inLocalidad);
		inLocalidad = lista;
		
		inLocalidad.setSelectedItem(localidadActual);
		contenedor.add(inLocalidad);
	}
	
	public void cargarTiposDeContacto() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<TipoContactoDTO> tipos = agenda.obtenerTiposDeContacto();
		JComboBox<TipoContactoDTO> lista = new JComboBox<TipoContactoDTO>();
		TipoContactoDTO tipoActual = tipos.get(0);
		
		lista.setBounds(140, 100, 200, 20);

		for (TipoContactoDTO tipo: tipos) {
			lista.addItem(tipo);
			if(tipo.getTipo_contacto_id()== persona.getTipo_contacto_id())
				tipoActual = tipo;
		}
			
		
		if (inTipoContacto != null)
			contenedor.remove(inTipoContacto);
		inTipoContacto = lista;
		
		inTipoContacto.setSelectedItem(tipoActual);
		contenedor.add(inTipoContacto);
	}


	public boolean validarInputs() {
		String mensaje = "Su formulario contiene entradas invalidas:\n";
		boolean isOk = true;
		
		if (!Validador.formatoLetraEspacio(inNombre.getText())) {
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios\n";
		}

		if (!Validador.formatoNumeroLetraEspacio(inCalle.getText())) {
			isOk = false;
			mensaje += "    -La CALLE solo puede consistir de letras, numeros y espacios\n";
		}

		if (!Validador.formatoNumero(inTelefono.getText())) {
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoNumero(inNumero.getText())) {
			isOk = false;
			mensaje += "    -El NUMERO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoNumero(inPiso.getText())) {
			isOk = false;
			mensaje += "    -El PISO solo puede consistir de numeros\n";
		}

		if (!Validador.formatoNumeroLetraEspacio(inDepto.getText())) {
			isOk = false;
			mensaje += "    -El DEPTO solo puede consistir de letras, numeros y espacios\n";
		}
		
		if (!Validador.formatoMail(inEmail.getText())) {
			isOk = false;
			mensaje += "    -El E-MAIL debe tener la forma A1@A1 (A1 representa uno o mas numeros o letras\n";
		}

		if (!isOk)
			JOptionPane.showMessageDialog(null, mensaje);
		
		return isOk;
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
	
	public PersonaDTO getContactoOriginal() {
		return persona;
	}
	
	public JButton getBtnModificarContacto() {
		return btnModificarContacto;
	}

	public JButton getBtnLocalidadABM() {
		return btnLocalidadABM;
	}
	
	public JButton getBtnTipoContactoABM() {
		return btnTipoContactoABM;
	}

}