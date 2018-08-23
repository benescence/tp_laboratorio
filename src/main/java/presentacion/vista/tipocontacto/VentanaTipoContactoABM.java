package presentacion.vista.tipocontacto;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class VentanaTipoContactoABM extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable tablaTiposDeContacto;
	private JButton btnAgregar, btnBorrar, btnModificar;
	private DefaultTableModel modeloTiposDeContacto;
	private String[] nombreColumnas = {"Tipo de contacto"};

	public VentanaTipoContactoABM() {
		super();
		setBounds(100, 100, 420, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 300);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spTiposDeContacto = new JScrollPane();
		spTiposDeContacto.setBounds(10, 10, 250, 280);
		panel.add(spTiposDeContacto);
		
		modeloTiposDeContacto = new DefaultTableModel(null, nombreColumnas);
		tablaTiposDeContacto = new JTable(modeloTiposDeContacto);
		spTiposDeContacto.setViewportView(tablaTiposDeContacto);
		
		btnAgregar = new JButton("A - Agregar");
		btnBorrar = new JButton("B - Borrar");
		btnModificar = new JButton("M - Modificar");
		
		int ancho = 120, alto = 20, columna = 270;
		btnAgregar.setBounds(columna, 10, ancho, alto);
		btnBorrar.setBounds(columna, 40, ancho, alto);		
		btnModificar.setBounds(columna, 70, ancho, alto);
		
		panel.add(btnAgregar);
		panel.add(btnBorrar);
		panel.add(btnModificar);
		
		setVisible(true);
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnModificar(){
		return btnModificar;
	}
	
	public DefaultTableModel getModeloTiposDeContacto()	{
		return modeloTiposDeContacto;
	}
	
	public JTable getTablaTiposDeContacto(){
		return tablaTiposDeContacto;
	}

	public String[] getNombreColumnas()	{
		return nombreColumnas;
	}

}