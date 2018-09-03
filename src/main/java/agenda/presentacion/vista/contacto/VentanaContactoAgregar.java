package agenda.presentacion.vista.contacto;

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

import agenda.negocios.Agenda;
import agenda.persistencia.mysql.DAOSQLFactory;
import agenda.persistencia.pojos.LocalidadDTO;
import agenda.persistencia.pojos.TipoContactoDTO;
import agenda.presentacion.controlador.Controlador;
import agenda.util.Validador;

import javax.swing.JComboBox;

public class VentanaContactoAgregar extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private JPanel contenedor;
	private JCalendar inCumple;
	private JTextField inEmail, inCalle, inDepto, inNumero, inPiso, inTelefono, inNombre, inApellido;
	private JButton btnAgregarContacto, btnLocalidadABM, btnTipoContactoABM;
	private JComboBox<LocalidadDTO> inLocalidad;
	private JComboBox <TipoContactoDTO> inTipoContacto;
	
	public VentanaContactoAgregar(Controlador controlador) {
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 600);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);

		// Medidas de la ventana
		int anchoLabelGrande = 120;
		int anchoLabelChico = 50;
		int anchoCajaGrande = 350;
		int anchoCajaChico = 100;
		int alto = 20;

		// Las etiquetas
		JLabel lblNombre = new JLabel("Nombre");
		JLabel lblApellido = new JLabel("Apellido");
		JLabel lblTelefono = new JLabel("Teléfono");
		JLabel lblMail = new JLabel("E- Mail");
		JLabel lblFecha = new JLabel("Fecha de cumpleaños");
		JLabel lblTipo = new JLabel("Tipo de contacto");		
		JLabel lblCalle = new JLabel("Calle");
		JLabel lblNumero = new JLabel("Número");
		JLabel lblPiso = new JLabel("Piso");
		JLabel lblDepto = new JLabel("Depto");
		JLabel lblLocalidad = new JLabel("Localidad");
		
		lblNombre.setBounds(   getColumnaX(1), getFilaY(1), anchoLabelGrande, alto);
		lblApellido.setBounds( getColumnaX(1), getFilaY(2), anchoLabelGrande, alto);
		lblTelefono.setBounds( getColumnaX(1), getFilaY(3), anchoLabelGrande, alto);
		lblMail.setBounds(     getColumnaX(1), getFilaY(4), anchoLabelGrande, alto);
		lblFecha.setBounds(    getColumnaX(1), getFilaY(9), anchoLabelGrande, alto);
		lblTipo.setBounds(     getColumnaX(1), getFilaY(5), anchoLabelGrande, alto);
		lblCalle.setBounds(    getColumnaX(1), getFilaY(7), anchoLabelGrande, alto);
		lblNumero.setBounds(   getColumnaX(1), getFilaY(8), anchoLabelChico, alto);
		lblPiso.setBounds(          180,       getFilaY(8), anchoLabelChico, alto);
		lblDepto.setBounds(         350,       getFilaY(8), anchoLabelChico, alto);
		lblLocalidad.setBounds(getColumnaX(1), getFilaY(6), anchoLabelGrande, alto);

		contenedor.add(lblNombre);
		contenedor.add(lblApellido);
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
		inCumple = new JCalendar();
		inCalle = new JTextField();
		inDepto = new JTextField();
		inNumero = new JTextField();
		inPiso = new JTextField();
		inNombre = new JTextField();
		inApellido = new JTextField();

		inNombre.setBounds(  getColumnaX(2), getFilaY(1), anchoCajaGrande, alto);
		inApellido.setBounds(getColumnaX(2), getFilaY(2), anchoCajaGrande, alto);
		inTelefono.setBounds(getColumnaX(2), getFilaY(3), anchoCajaGrande, alto);
		inEmail.setBounds(   getColumnaX(2), getFilaY(4), anchoCajaGrande, alto);
		inCumple.setBounds(  getColumnaX(2), getFilaY(9), 200, 200);
		inCalle.setBounds(   getColumnaX(2), getFilaY(7), anchoCajaGrande, alto);
		inNumero.setBounds(  70,  getFilaY(8), anchoCajaChico, alto);
		inPiso.setBounds(    230, getFilaY(8), anchoCajaChico, alto);
		inDepto.setBounds(   390, getFilaY(8), anchoCajaChico, alto);

		// valores por defecto
		inApellido.setText("-");
		inTelefono.setText("-");
		inEmail.setText("-");
		inCalle.setText("-");
		inNumero.setText("-");
		inPiso.setText("-");				
		inDepto.setText("-");
		
		contenedor.add(inNombre);
		contenedor.add(inApellido);
		contenedor.add(inEmail);
		contenedor.add(inTelefono);
		contenedor.add(inCumple);
		contenedor.add(inCalle);
		contenedor.add(inNumero );
		contenedor.add(inDepto);
		contenedor.add(inPiso);
		
		// Los desplegables
		cargarLocalidades();
		cargarTiposDeContacto();

		// Los botones
		btnAgregarContacto = new JButton("Agregar nuevo contacto");
		btnTipoContactoABM = new JButton("Administrar");
		btnLocalidadABM = new JButton("Administrar");
		
		
		btnAgregarContacto.addActionListener(this.controlador);
		btnLocalidadABM.addActionListener(this.controlador);
		btnTipoContactoABM.addActionListener(this.controlador);
		
		btnAgregarContacto.setBounds(140, getFilaY(10), 200, 25);
		btnLocalidadABM.setBounds(getColumnaX(3), getFilaY(6), 140, alto);
		btnTipoContactoABM.setBounds(getColumnaX(3), getFilaY(5), 140, alto);

		contenedor.add(btnAgregarContacto);
		contenedor.add(btnTipoContactoABM);
		contenedor.add(btnLocalidadABM);
		
		this.setVisible(true);
	}
	
	public void cargarLocalidades() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		JComboBox<LocalidadDTO> lista = new JComboBox<LocalidadDTO>();
		lista.setBounds(getColumnaX(2), getFilaY(6), 200, 20);
		for (LocalidadDTO localidad : localidades)
			lista.addItem(localidad);
		
		if (inLocalidad != null)
			contenedor.remove(inLocalidad);
		inLocalidad = lista;
		contenedor.add(inLocalidad);
	}
	
	public void cargarTiposDeContacto() {
		Agenda agenda = new Agenda(new DAOSQLFactory());
		List<TipoContactoDTO> tipos = agenda.obtenerTiposDeContacto();
		JComboBox<TipoContactoDTO> lista = new JComboBox<TipoContactoDTO>();
		lista.setBounds(getColumnaX(2), getFilaY(5), 200, 20);

		for (TipoContactoDTO tipo: tipos)
			lista.addItem(tipo);
		
		if (inTipoContacto != null)
			contenedor.remove(inTipoContacto);
		inTipoContacto = lista;
		contenedor.add(inTipoContacto);
	}
	
	public boolean validarInputs() {
		String mensaje = "Su formulario contiene entradas invalidas:\n";
		boolean isOk = true;
		
		if (!Validador.formatoLetraEspacio(inNombre.getText())) {
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios\n";
		}

		if (!Validador.formatoApellido(inApellido.getText())) {
			isOk = false;
			mensaje += "    -El APELLIDO solo puede consistir de letras y espacios\n";
		}

		if (!Validador.formatoCalle(inCalle.getText())) {
			isOk = false;
			mensaje += "    -La CALLE solo puede consistir de letras, numeros y espacios\n";
		}

		if (!Validador.formatoTelefono(inTelefono.getText())) {
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoNumero(inNumero.getText())) {
			isOk = false;
			mensaje += "    -El NUMERO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoPiso(inPiso.getText())) {
			isOk = false;
			mensaje += "    -El PISO solo puede consistir de numeros\n";
		}

		if (!Validador.formatoDepartamento(inDepto.getText())) {
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
	
	
	// Getters para valores de posicion
	private int getFilaY(int fila) {
		int[] filas = {10, 40, 70, 100, 130, 160, 190, 220, 250, 460}; 
		return filas[fila-1];
	}

	private int getColumnaX(int columna) {
		int[] columnas = {10, 140, 350};
		return columnas[columna-1];
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

	public String getApellido() {
		return inApellido.getText();
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
		java.util.Date utilDate = inCumple.getDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public JButton getBtnAgregarContacto() {
		return btnAgregarContacto;
	}

	public JButton getBtnLocalidadABM() {
		return btnLocalidadABM;
	}
	
	public JButton getBtnTipoContactoABM() {
		return btnTipoContactoABM;
	}

}