package agenda.presentacion.vista.configurar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaConfigurar extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JTextField inIP, inPuerto, inUsuario, inPassword;
	private JButton btnProbarConexion, btnAceptar;
	
	public VentanaConfigurar() {
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 250);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);

		// MEDIDAS GENERALES
		int anchoLabel = 100;
		int anchoCaja = 250;
		int alto = 20;

		// ETIQUETA DE TEXTO
		JLabel lblUsuario = new JLabel("Usuario");
		JLabel lblPassword = new JLabel("Password");		
		JLabel lblIP = new JLabel("IP");
		JLabel lblPuerto = new JLabel("Puerto");
		
		lblUsuario.setBounds( getColumnaX(1), getFilaY(1), anchoLabel, alto);
		lblPassword.setBounds(getColumnaX(1), getFilaY(2), anchoLabel, alto);
		lblIP.setBounds(      getColumnaX(1), getFilaY(3), anchoLabel, alto);
		lblPuerto.setBounds(  getColumnaX(1), getFilaY(4), anchoLabel, alto);

		contenedor.add(lblUsuario);
		contenedor.add(lblPassword);
		contenedor.add(lblIP);
		contenedor.add(lblPuerto);
		
		// CAJAS DE TEXTO
		inUsuario = new JTextField();
		inPassword = new JTextField();
		inIP = new JTextField();
		inPuerto = new JTextField();

		inUsuario.setBounds( getColumnaX(2), getFilaY(1), anchoCaja, alto);
		inPassword.setBounds(getColumnaX(2), getFilaY(2), anchoCaja, alto);
		inIP.setBounds(      getColumnaX(2), getFilaY(3), anchoCaja, alto);
		inPuerto.setBounds(  getColumnaX(2), getFilaY(4), anchoCaja, alto);

		// VALORES POR DEFECTO
		inIP.setText("localhost");
		inPuerto.setText("3306");
		inUsuario.setText("root");
		inPassword.setText("root");
		
		contenedor.add(inIP);
		contenedor.add(inPuerto);
		contenedor.add(inUsuario);
		contenedor.add(inPassword);

		// Los botones
		btnAceptar = new JButton("Aceptar");
		btnProbarConexion = new JButton("Probar conexion");
		
		btnAceptar.setBounds(20, getFilaY(6), 170, alto+5);
		btnProbarConexion.setBounds(210, getFilaY(6), 170, alto+5);

		contenedor.add(btnAceptar);
		contenedor.add(btnProbarConexion);
		
		setVisible(true);
	}
		/*
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

		if (!Validador.formatoCalle(inPuerto.getText())) {
			isOk = false;
			mensaje += "    -La CALLE solo puede consistir de letras, numeros y espacios\n";
		}

		if (!Validador.formatoTelefono(inTelefono.getText())) {
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoNumero(inPassword.getText())) {
			isOk = false;
			mensaje += "    -El NUMERO solo puede consistir de numeros\n";
		}
		
		if (!Validador.formatoPiso(inPiso.getText())) {
			isOk = false;
			mensaje += "    -El PISO solo puede consistir de numeros\n";
		}

		if (!Validador.formatoDepartamento(inUsuario.getText())) {
			isOk = false;
			mensaje += "    -El DEPTO solo puede consistir de letras, numeros y espacios\n";
		}
		
		if (!Validador.formatoMail(inIP.getText())) {
			isOk = false;
			mensaje += "    -El E-MAIL debe tener la forma A1@A1 (A1 representa uno o mas numeros o letras\n";
		}

		if (!isOk)
			JOptionPane.showMessageDialog(null, mensaje);
		
		return isOk;
	}
	*/
	
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
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public JButton getBtnProbarconexion() {
		return btnProbarConexion;
	}

	public String  getIP() {
		return inIP.getText();
	}

	public String getPuerto() {
		return inPuerto.getText();
	}

	public String getUsuario() {
		return inUsuario.getText();
	}

	public String getPassword() {
		return inPassword.getText();
	}

}