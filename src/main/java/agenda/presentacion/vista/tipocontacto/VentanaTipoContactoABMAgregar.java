package agenda.presentacion.vista.tipocontacto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import agenda.presentacion.controlador.ControladorTipoContactoABM;
import agenda.util.Validador;

public class VentanaTipoContactoABMAgregar extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorTipoContactoABM controlador;
	private JPanel contenedor_principal;
	private JTextField inTipoDeContacto;
	private JButton btnAgregar;
	
	public VentanaTipoContactoABMAgregar(ControladorTipoContactoABM controladorTipoContactoABM) {
		super();
		controlador = controladorTipoContactoABM;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 110);
		contenedor_principal = new JPanel();
		contenedor_principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor_principal);
		contenedor_principal.setLayout(null);

		JLabel lblLocalidad = new JLabel("Tipo de contacto");		
		lblLocalidad.setBounds(10, 10, 80, 20);
		contenedor_principal.add(lblLocalidad);
		
		inTipoDeContacto = new JTextField();
		inTipoDeContacto.setColumns(10);
		inTipoDeContacto.setBounds(100, 10, 140, 20);
		contenedor_principal.add(inTipoDeContacto);
		
		btnAgregar= new JButton("Agregar tipo de contacto");
		btnAgregar.addActionListener(controlador);
		btnAgregar.setBounds(10, 40, 230, 20);
		contenedor_principal.add(btnAgregar);
		
		setVisible(true);
	}
	
	public boolean validarInputs() {
		String mensaje = "Su formulario contiene entradas invalidas:\n";
		boolean isOk = true;
		
		if (!Validador.formatoLetraEspacio(inTipoDeContacto.getText())) {
			isOk = false;
			mensaje += "    -El TIPO DE CONTACTO solo puede consistir de letras y espacios\n";
		}
		
		if (!isOk)
			JOptionPane.showMessageDialog(null, mensaje);
		
		return isOk;
	}

	public String getDescripcionDeTipoDeContacto() {
		return inTipoDeContacto.getText();
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

}