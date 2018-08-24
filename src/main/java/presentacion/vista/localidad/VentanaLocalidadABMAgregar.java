package presentacion.vista.localidad;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import presentacion.controlador.ControladorLocalidadABM;
import presentacion.vista.util.Validador;

public class VentanaLocalidadABMAgregar extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorLocalidadABM controlador;
	private JPanel contenedor;
	private JTextField inLocalidad;
	private JButton btnAgregar;
	
	public VentanaLocalidadABMAgregar(ControladorLocalidadABM controladorLocalidadABM) {
		super();
		controlador = controladorLocalidadABM;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 110);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad");		
		lblLocalidad.setBounds(10, 10, 80, 20);
		contenedor.add(lblLocalidad);
		
		inLocalidad = new JTextField();
		inLocalidad.setColumns(10);
		inLocalidad.setBounds(100, 10, 140, 20);
		contenedor.add(inLocalidad);
		
		btnAgregar= new JButton("Agregar localidad");
		btnAgregar.addActionListener(controlador);
		btnAgregar.setBounds(10, 40, 230, 20);
		contenedor.add(btnAgregar);
		
		setVisible(true);
	}
	
	public boolean validarInputs() {
		String mensaje = "Su formulario contiene entradas invalidas:\n";
		boolean isOk = true;
		
		if (!Validador.formatoNumeroLetraEspacio(inLocalidad.getText())) {
			isOk = false;
			mensaje += "    -La LOCALIDAD solo puede consistir de letras, numeros y espacios\n";
		}
		
		if (!isOk)
			JOptionPane.showMessageDialog(null, mensaje);
		
		return isOk;
	}
	
	public String getDescripcionDeLocalidad() {
		return inLocalidad.getText();
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

}