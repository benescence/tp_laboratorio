package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.vista.tipocontacto.VentanaTipoContactoABM;
import presentacion.vista.tipocontacto.VentanaTipoContactoABMAgregar;
import presentacion.vista.tipocontacto.VentanaTipoContactoABMModificar;
import dto.TipoContactoDTO;

public class ControladorTipoContactoABM implements ActionListener {
	private List<TipoContactoDTO> tipos_de_contacto_en_tabla;
	private VentanaTipoContactoABM ventanaTipoContactoABM;
	private VentanaTipoContactoABMAgregar ventanaTipoContactoABMAgregar;
	private VentanaTipoContactoABMModificar ventanaTipoContactoABMModificar;
	private Agenda agenda;
	
	public ControladorTipoContactoABM(VentanaTipoContactoABM pVentanaTipoContactoABM, Agenda pAgenda) {
		ventanaTipoContactoABM = pVentanaTipoContactoABM;
		agenda = pAgenda;
		ventanaTipoContactoABM.getBtnAgregar().addActionListener(this);
		ventanaTipoContactoABM.getBtnBorrar().addActionListener(this);
		ventanaTipoContactoABM.getBtnModificar().addActionListener(this);
		recargarTabla();
	}
	
	private void recargarTabla() {
		ventanaTipoContactoABM.getModeloTiposDeContacto().setRowCount(0);
		ventanaTipoContactoABM.getModeloTiposDeContacto().setColumnCount(0);
		ventanaTipoContactoABM.getModeloTiposDeContacto().setColumnIdentifiers(ventanaTipoContactoABM.getNombreColumnas());
		
		tipos_de_contacto_en_tabla = agenda.obtenerTiposDeContacto();
		for (TipoContactoDTO tipo: tipos_de_contacto_en_tabla) {
			Object[] fila = {tipo.getDescripcion()};
			ventanaTipoContactoABM.getModeloTiposDeContacto().addRow(fila);
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// BOTONES DEL ABM PRINCIPAL
		
		// AGREGAR TIPO DE CONTACTO
		if (e.getSource() == ventanaTipoContactoABM.getBtnAgregar())	{
			ventanaTipoContactoABMAgregar = new VentanaTipoContactoABMAgregar(this);
		}
		
		// BORRAR TIPO DE CONTACTO	
		else if(e.getSource() == ventanaTipoContactoABM.getBtnBorrar()) {
			int[] filas_seleccionadas = ventanaTipoContactoABM.getTablaTiposDeContacto().getSelectedRows();
			for (int fila:filas_seleccionadas)
				agenda.borrarTipoDeContacto(tipos_de_contacto_en_tabla.get(fila));
			
			recargarTabla();
		}

		// MODIFICAR TIPO DE CONTACTO	
		else if(e.getSource() == ventanaTipoContactoABM.getBtnModificar()) {
			int[] filas_seleccionadas = ventanaTipoContactoABM.getTablaTiposDeContacto().getSelectedRows();
			if (filas_seleccionadas.length>0) {
				TipoContactoDTO TipoContactoDTO = tipos_de_contacto_en_tabla.get(filas_seleccionadas[0]);
				ventanaTipoContactoABMModificar = new VentanaTipoContactoABMModificar(this, TipoContactoDTO);
			}
		}
		
		
		// BOTONES DE LA VENTANA AGREGAR TIPO DE CONTACTO
		if (ventanaTipoContactoABMAgregar != null) {
			// AGREGAR TIPO DE CONTACTO
			if(e.getSource() == ventanaTipoContactoABMAgregar.getBtnAgregar()) {
				TipoContactoDTO TipoContacto = new TipoContactoDTO(-1, ventanaTipoContactoABMAgregar.getDescripcionDeTipoDeContacto());
				agenda.agregarTipoDeContacto(TipoContacto);
				recargarTabla();
				ventanaTipoContactoABMAgregar.dispose();
			}
		}
		
		// BOTONES DE LA VENTANA MODIFICAR TIPO DE CONTACTO
		if (ventanaTipoContactoABMModificar != null) {
			// MODIFICAR TIPO DE CONTACTO
			if(e.getSource() == ventanaTipoContactoABMModificar.getBtnModificar()) {
				TipoContactoDTO TipoContacto = new TipoContactoDTO(
						ventanaTipoContactoABMModificar.getTipoDeContactoID(),
						ventanaTipoContactoABMModificar.getDescripcionDeTipoDeContacto()
						);
				agenda.modificarTipoDeContacto(TipoContacto);
				recargarTabla();
				ventanaTipoContactoABMModificar.dispose();
			}
		}
	}
	
}