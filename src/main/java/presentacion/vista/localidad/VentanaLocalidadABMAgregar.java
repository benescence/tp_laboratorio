package presentacion.vista.localidad;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import presentacion.controlador.ControladorLocalidadABM;

public class VentanaLocalidadABMAgregar extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorLocalidadABM controlador;
	private JPanel contenedor_principal;
	private JTextField inLocalidad;
	private JButton btnAgregar;
	
	public VentanaLocalidadABMAgregar(ControladorLocalidadABM controladorLocalidadABM) {
		super();
		controlador = controladorLocalidadABM;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 110);
		contenedor_principal = new JPanel();
		contenedor_principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor_principal);
		contenedor_principal.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad");		
		lblLocalidad.setBounds(10, 10, 80, 20);
		contenedor_principal.add(lblLocalidad);
		
		inLocalidad = new JTextField();
		inLocalidad.setColumns(10);
		inLocalidad.setBounds(100, 10, 140, 20);
		contenedor_principal.add(inLocalidad);
		
		btnAgregar= new JButton("Agregar localidad");
		btnAgregar.addActionListener(controlador);
		btnAgregar.setBounds(10, 40, 230, 20);
		contenedor_principal.add(btnAgregar);
		
		setVisible(true);
	}
		
	public String getDescripcionDeLocalidad() {
		return inLocalidad.getText();
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

}