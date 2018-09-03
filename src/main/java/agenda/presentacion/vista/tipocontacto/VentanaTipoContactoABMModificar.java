package agenda.presentacion.vista.tipocontacto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import agenda.persistencia.pojos.TipoContactoDTO;
import agenda.presentacion.controlador.ControladorTipoContactoABM;
import agenda.presentacion.vista.util.Validador;

public class VentanaTipoContactoABMModificar extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorTipoContactoABM controlador;
	private JPanel contenedor;
	private JTextField inTipoDeContacto;
	private JButton btnModificar;
	private TipoContactoDTO tipoContacto;
	
	public VentanaTipoContactoABMModificar(ControladorTipoContactoABM controladorTipoContactoABM, TipoContactoDTO pTipoContacto) {
		super();
		controlador = controladorTipoContactoABM;
		tipoContacto = pTipoContacto;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 110);
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");		
		lblTipoDeContacto.setBounds(10, 10, 80, 20);
		contenedor.add(lblTipoDeContacto);
		
		inTipoDeContacto = new JTextField();
		inTipoDeContacto.setColumns(10);
		inTipoDeContacto.setBounds(100, 10, 140, 20);
		inTipoDeContacto.setText(tipoContacto.getDescripcion());
		contenedor.add(inTipoDeContacto);
		
		btnModificar= new JButton("Modificar tipo de contacto");
		btnModificar.addActionListener(controlador);
		btnModificar.setBounds(10, 40, 230, 20);
		contenedor.add(btnModificar);
		
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
	
	public Integer getTipoDeContactoID() {
		return tipoContacto.getTipo_contacto_id();
	}
	
	public JButton getBtnModificar() {
		return btnModificar;
	}

}