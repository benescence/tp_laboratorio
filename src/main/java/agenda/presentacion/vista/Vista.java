package agenda.presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import agenda.persistencia.Conexion;

import javax.swing.JButton;

public class Vista {
	private JFrame ventana;
	private JTable tablaContactos;
	private JButton btnAgregar, btnBorrar, btnModificar, btnReporte, btnConfigurar;
	private DefaultTableModel modelContactos;
	private String[] nombreColumnas = {"Nombre", "Apellido", "Teléfono","E-mail","Calle","Número","Piso","Depto","Localidad","Cumpleaños", "Tipo de contacto"};

	public Vista() {
		super();
		ventana = new JFrame();
		ventana.setBounds(100, 100, 1100, 400);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1100, 400);
		ventana.getContentPane().add(panel);
		panel.setLayout(null);
		
		modelContactos = new DefaultTableModel(null, nombreColumnas);
		tablaContactos = new JTable(modelContactos);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 10, 1050, 260);
		spPersonas.setViewportView(tablaContactos);
		panel.add(spPersonas);		

		btnAgregar = new JButton("Agregar contacto");
		btnBorrar = new JButton("Borrar contacto");
		btnModificar = new JButton("Modificar contacto");
		btnReporte = new JButton("Mostrar reporte");
		btnConfigurar = new JButton("Configurar conexion");
		
		int fila = 280, ancho = 180, alto = 25;
		btnAgregar.setBounds(10, fila, ancho, alto);
		btnBorrar.setBounds(230, fila, ancho, alto);
		btnModificar.setBounds(450, fila, ancho, alto);		
		btnReporte.setBounds(670, fila, ancho, alto);
		btnConfigurar.setBounds(890, fila, ancho, alto);
		
		panel.add(btnAgregar);
		panel.add(btnBorrar);
		panel.add(btnModificar);
		panel.add(btnReporte);
		panel.add(btnConfigurar);
	}
	
	public void show() {
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirmar = JOptionPane.showOptionDialog(
		             null, "¿Salir de la Agenda?", 
		             "Confirmacion", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirmar == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		ventana.setVisible(true);
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnReporte(){
		return btnReporte;
	}

	public JButton getBtnModificar(){
		return btnModificar;
	}

	public JButton getBtnConfigurar(){
		return btnConfigurar;
	}
	
	public JFrame getVentana(){
		return ventana;
	}
	
	public DefaultTableModel getModelContactos()	{
		return modelContactos;
	}
	
	public JTable getTablaContactos(){
		return tablaContactos;
	}

	public String[] getNombreColumnas()	{
		return nombreColumnas;
	}

}