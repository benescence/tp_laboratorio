package agenda.presentacion.vista.localidad;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class VentanaLocalidadABM extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable tablaLocalidades;
	private JButton btnAgregar, btnBorrar, btnModificar;
	private DefaultTableModel modeloLocalidades;
	private String[] nombreColumnas = {"Localidad"};

	public VentanaLocalidadABM() {
		super();
		setBounds(100, 100, 420, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 300);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 10, 250, 280);
		panel.add(spLocalidades);
		
		modeloLocalidades = new DefaultTableModel(null, nombreColumnas);
		tablaLocalidades = new JTable(modeloLocalidades);
		spLocalidades.setViewportView(tablaLocalidades);
		
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
	
	public DefaultTableModel getModeloLocalidades()	{
		return modeloLocalidades;
	}
	
	public JTable getTablaLocalidades(){
		return tablaLocalidades;
	}

	public String[] getNombreColumnas()	{
		return nombreColumnas;
	}

}