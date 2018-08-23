package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.vista.localidad.VentanaLocalidadABM;
import presentacion.vista.localidad.VentanaLocalidadABMAgregar;
import presentacion.vista.localidad.VentanaLocalidadABMModificar;
import dto.LocalidadDTO;

public class ControladorLocalidadABM implements ActionListener {
	private List<LocalidadDTO> localidades_en_tabla;
	private VentanaLocalidadABM ventanaLocalidadABM;
	private VentanaLocalidadABMAgregar ventanaLocalidadABMAgregar;
	private VentanaLocalidadABMModificar ventanaLocalidadABMModificar;
	private Agenda agenda;
	
	public ControladorLocalidadABM(VentanaLocalidadABM pVentanaLocalidadABM, Agenda pAgenda) {
		ventanaLocalidadABM = pVentanaLocalidadABM;
		agenda = pAgenda;
		pVentanaLocalidadABM.getBtnAgregar().addActionListener(this);
		pVentanaLocalidadABM.getBtnBorrar().addActionListener(this);
		pVentanaLocalidadABM.getBtnModificar().addActionListener(this);
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
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// BOTONES DEL ABM PRINCIPAL
		
		// AGREGAR LOCALIDAD
		if (e.getSource() == ventanaLocalidadABM.getBtnAgregar())	{
			ventanaLocalidadABMAgregar = new VentanaLocalidadABMAgregar(this);
		}
		
		// BORRAR LOCALIDAD	
		else if(e.getSource() == ventanaLocalidadABM.getBtnBorrar()) {
			int[] filas_seleccionadas = ventanaLocalidadABM.getTablaLocalidades().getSelectedRows();
			for (int fila:filas_seleccionadas)
				agenda.borrarLocalidad(localidades_en_tabla.get(fila));
			
			recargarTabla();
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
			if(e.getSource() == ventanaLocalidadABMAgregar.getBtnAgregar()) {
				LocalidadDTO localidad = new LocalidadDTO(-1, ventanaLocalidadABMAgregar.getDescripcionDeLocalidad());
				agenda.agregarLocalidad(localidad);
				recargarTabla();
				ventanaLocalidadABMAgregar.dispose();
			}
		}
		
		// BOTONES DE LA VENTANA MODIFICAR LOCALIDAD
		if (ventanaLocalidadABMModificar != null) {
			// MODIFICAR LOCALIDAD
			if(e.getSource() == ventanaLocalidadABMModificar.getBtnModificar()) {
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
	
}