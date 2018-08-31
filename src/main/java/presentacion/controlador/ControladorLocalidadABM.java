package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.dto.LocalidadDTO;
import persistencia.dto.PersonaDTO;
import presentacion.vista.contacto.VentanaContactoAgregar;
import presentacion.vista.contacto.VentanaContactoModificar;
import presentacion.vista.localidad.VentanaLocalidadABM;
import presentacion.vista.localidad.VentanaLocalidadABMAgregar;
import presentacion.vista.localidad.VentanaLocalidadABMModificar;

public class ControladorLocalidadABM implements ActionListener {
	private Controlador controlador;
	private List<LocalidadDTO> localidades_en_tabla;
	private VentanaLocalidadABM ventanaABM;
	private VentanaLocalidadABMAgregar ventanaABMAgregar;
	private VentanaLocalidadABMModificar ventanaABMModificar;
	private VentanaContactoAgregar ventanaContactoAgregar;
	private VentanaContactoModificar ventanaContactoModificar;
	private Agenda agenda;
	
	public ControladorLocalidadABM(Controlador controlador, VentanaLocalidadABM ventanaLocalidadABM, VentanaContactoAgregar ventanaContactoAgregar, Agenda agenda) {
		this.controlador = controlador;
		this.ventanaABM = ventanaLocalidadABM;
		this.ventanaContactoAgregar = ventanaContactoAgregar;
		this.agenda = agenda;
		inicializar();
	}
	
	public ControladorLocalidadABM(Controlador controlador, VentanaLocalidadABM ventanaLocalidadABM, VentanaContactoModificar ventanaContactoModificar, Agenda agenda) {
		this.controlador = controlador;
		this.ventanaABM = ventanaLocalidadABM;
		this.ventanaContactoModificar = ventanaContactoModificar;
		this.agenda = agenda;
		inicializar();
	}
	
	private void inicializar() {
		ventanaABM.getBtnAgregar().addActionListener(this);
		ventanaABM.getBtnBorrar().addActionListener(this);
		ventanaABM.getBtnModificar().addActionListener(this);
		recargarTabla();
	}
	
	private void recargarTabla() {
		ventanaABM.getModeloLocalidades().setRowCount(0);
		ventanaABM.getModeloLocalidades().setColumnCount(0);
		ventanaABM.getModeloLocalidades().setColumnIdentifiers(ventanaABM.getNombreColumnas());
		
		localidades_en_tabla = agenda.obtenerLocalidades();
		for (LocalidadDTO localidad : localidades_en_tabla) {
			Object[] fila = {localidad.getDescripcion()};
			ventanaABM.getModeloLocalidades().addRow(fila);
		}
		
		if (ventanaContactoAgregar != null)
			ventanaContactoAgregar.cargarLocalidades();
		
		else if (ventanaContactoModificar != null)
			ventanaContactoModificar.cargarLocalidades();
	}
	
	public void actionPerformed(ActionEvent e) {		
		// **********************  BOTONES DEL ABM PRINCIPAL *******************************
		
		// AGREGAR LOCALIDAD
		if (e.getSource() == ventanaABM.getBtnAgregar())	{
			ventanaABMAgregar = new VentanaLocalidadABMAgregar(this);
		}
		
		// BORRAR LOCALIDAD	
		else if(e.getSource() == ventanaABM.getBtnBorrar()) {
            borrarLocalidades();
			
		}

		// MODIFICAR LOCALIDAD	
		else if(e.getSource() == ventanaABM.getBtnModificar()) {
			int[] filas_seleccionadas = ventanaABM.getTablaLocalidades().getSelectedRows();
			if (filas_seleccionadas.length>0) {
				LocalidadDTO localidadDTO = localidades_en_tabla.get(filas_seleccionadas[0]);
				ventanaABMModificar = new VentanaLocalidadABMModificar(this, localidadDTO);
			}
			
		}
		
		
		// BOTONES DE LA VENTANA AGREGAR LOCALIDAD
		if (ventanaABMAgregar != null) {
			// AGREGAR LOCALIDAD
			if(e.getSource() == ventanaABMAgregar.getBtnAgregar() && ventanaABMAgregar.validarInputs()) {
				LocalidadDTO localidad = new LocalidadDTO(-1, ventanaABMAgregar.getDescripcionDeLocalidad());
				agenda.agregarLocalidad(localidad);
				recargarTabla();
				ventanaABMAgregar.dispose();
			}
		}
		
		// BOTONES DE LA VENTANA MODIFICAR LOCALIDAD
		if (ventanaABMModificar != null) {
			// MODIFICAR LOCALIDAD
			if(e.getSource() == ventanaABMModificar.getBtnModificar() && ventanaABMModificar.validarInputs()) {
				LocalidadDTO localidad = new LocalidadDTO(
						ventanaABMModificar.getLocalidadID(),
						ventanaABMModificar.getDescripcionDeLocalidad()
						);
				agenda.modificarLocalidad(localidad);
				recargarTabla();
				ventanaABMModificar.dispose();
			}
			controlador.recargarTabla();
		}
	}

	private void borrarLocalidades() {
		String mensaje= "Las siguientes Localidades no se pueden borrar porque estan en uso\n";
		boolean isOk = true;
		int[] filas_seleccionadas = ventanaABM.getTablaLocalidades().getSelectedRows();
		
		try {
			for (int fila: filas_seleccionadas) {
				LocalidadDTO localidad = localidades_en_tabla.get(fila);
				
				if (!localidadEnUso(localidad))
					agenda.borrarLocalidad(localidad);				
				else {
					isOk = false;
					mensaje += "    -"+ localidad.getDescripcion()+ "\n";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!isOk)
			JOptionPane.showMessageDialog(null, mensaje);
		
		recargarTabla();
	}
	
	private boolean localidadEnUso(LocalidadDTO localidad) {
		boolean enUso = false;
		for (PersonaDTO persona : agenda.obtenerPersonas())
			enUso = enUso || persona.getLocalidad_id() == localidad.getLocalidad_id();
		
		return enUso;		
	}
	
}