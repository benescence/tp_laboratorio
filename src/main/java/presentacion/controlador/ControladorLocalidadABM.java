package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.dto.LocalidadDTO;
import presentacion.vista.contacto.VentanaContactoAgregar;
import presentacion.vista.contacto.VentanaContactoModificar;
import presentacion.vista.localidad.VentanaLocalidadABM;
import presentacion.vista.localidad.VentanaLocalidadABMAgregar;
import presentacion.vista.localidad.VentanaLocalidadABMModificar;

public class ControladorLocalidadABM implements ActionListener {
	private List<LocalidadDTO> localidades_en_tabla;
	private VentanaLocalidadABM ventanaLocalidadABM;
	private VentanaLocalidadABMAgregar ventanaLocalidadABMAgregar;
	private VentanaLocalidadABMModificar ventanaLocalidadABMModificar;
	private VentanaContactoAgregar ventanaContactoAgregar;
	private VentanaContactoModificar ventanaContactoModificar;
	private Agenda agenda;
	
	public ControladorLocalidadABM(VentanaLocalidadABM ventanaLocalidadABM, VentanaContactoAgregar ventanaContactoAgregar, Agenda agenda) {
		this.ventanaLocalidadABM = ventanaLocalidadABM;
		this.ventanaContactoAgregar = ventanaContactoAgregar;
		this.agenda = agenda;
		inicializar();
	}
	
	public ControladorLocalidadABM(VentanaLocalidadABM ventanaLocalidadABM, VentanaContactoModificar ventanaContactoModificar, Agenda agenda) {
		this.ventanaLocalidadABM = ventanaLocalidadABM;
		this.ventanaContactoModificar = ventanaContactoModificar;
		this.agenda = agenda;
		inicializar();
	}
	
	private void inicializar() {
		ventanaLocalidadABM.getBtnAgregar().addActionListener(this);
		ventanaLocalidadABM.getBtnBorrar().addActionListener(this);
		ventanaLocalidadABM.getBtnModificar().addActionListener(this);
		recargarTabla();
	}
	
	private void recargarTabla() {
		ventanaLocalidadABM.getModeloLocalidades().setRowCount(0);
		ventanaLocalidadABM.getModeloLocalidades().setColumnCount(0);
		ventanaLocalidadABM.getModeloLocalidades().setColumnIdentifiers(ventanaLocalidadABM.getNombreColumnas());
		
		localidades_en_tabla = agenda.obtenerLocalidades();
		for (LocalidadDTO localidad : localidades_en_tabla) {
			Object[] fila = {localidad.getDescripcion()};
			ventanaLocalidadABM.getModeloLocalidades().addRow(fila);
		}
		
		if (ventanaContactoAgregar != null)
			ventanaContactoAgregar.cargarLocalidades();
		
		else if (ventanaContactoModificar != null)
			ventanaContactoModificar.cargarLocalidades();
	}
	
	public void actionPerformed(ActionEvent e) {		
		// **********************  BOTONES DEL ABM PRINCIPAL *******************************
		
		// AGREGAR LOCALIDAD
		if (e.getSource() == ventanaLocalidadABM.getBtnAgregar())	{
			ventanaLocalidadABMAgregar = new VentanaLocalidadABMAgregar(this);
		}
		
		// BORRAR LOCALIDAD	
		else if(e.getSource() == ventanaLocalidadABM.getBtnBorrar()) {
            borrarLocalidades();
			
		}

		// MODIFICAR LOCALIDAD	
		else if(e.getSource() == ventanaLocalidadABM.getBtnModificar()) {
			int[] filas_seleccionadas = ventanaLocalidadABM.getTablaLocalidades().getSelectedRows();
			if (filas_seleccionadas.length>0) {
				LocalidadDTO localidadDTO = localidades_en_tabla.get(filas_seleccionadas[0]);
				ventanaLocalidadABMModificar = new VentanaLocalidadABMModificar(this, localidadDTO);
			}
		}
		
		
		// BOTONES DE LA VENTANA AGREGAR LOCALIDAD
		if (ventanaLocalidadABMAgregar != null) {
			// AGREGAR LOCALIDAD
			if(e.getSource() == ventanaLocalidadABMAgregar.getBtnAgregar() && ventanaLocalidadABMAgregar.validarInputs()) {
				LocalidadDTO localidad = new LocalidadDTO(-1, ventanaLocalidadABMAgregar.getDescripcionDeLocalidad());
				agenda.agregarLocalidad(localidad);
				recargarTabla();
				ventanaLocalidadABMAgregar.dispose();
			}
		}
		
		// BOTONES DE LA VENTANA MODIFICAR LOCALIDAD
		if (ventanaLocalidadABMModificar != null) {
			// MODIFICAR LOCALIDAD
			if(e.getSource() == ventanaLocalidadABMModificar.getBtnModificar() && ventanaLocalidadABMModificar.validarInputs()) {
				LocalidadDTO localidad = new LocalidadDTO(
						ventanaLocalidadABMModificar.getLocalidadID(),
						ventanaLocalidadABMModificar.getDescripcionDeLocalidad()
						);
				agenda.modificarLocalidad(localidad);
				recargarTabla();
				ventanaLocalidadABMModificar.dispose();
			}
			
		}
	}

	private void borrarLocalidades() {
		String mensaje= "Las siguientes Localidades no se pudieron borrar\n";
		String isOk = "";
		String nombreLocalidad = "";	
		int[] filas_seleccionadas = ventanaLocalidadABM.getTablaLocalidades().getSelectedRows();
		
		for (int fila:filas_seleccionadas) {
			try {
				nombreLocalidad = localidades_en_tabla.get(fila).getDescripcion();
				agenda.borrarLocalidad(localidades_en_tabla.get(fila));
			} catch (Exception e1) {
				isOk = "ERROR";
				mensaje += "    -"+ nombreLocalidad+ "\n";
			}
		}
		
		if (!isOk.equals("")){
			JOptionPane.showMessageDialog(null, mensaje);
		}
		
		recargarTabla();
	}
	
}

