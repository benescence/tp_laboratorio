package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.Vista;
import presentacion.vista.contacto.VentanaContactoAgregar;
import presentacion.vista.contacto.VentanaContactoModificar;
import presentacion.vista.localidad.VentanaLocalidadABM;
import presentacion.vista.tipocontacto.VentanaTipoContactoABM;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private VentanaContactoAgregar ventanaContactoAgregar;
	private VentanaLocalidadABM ventanaLocalidadABM;
	private VentanaTipoContactoABM ventanaTipoContactoABM;
	private VentanaContactoModificar ventanaEditarContacto;
	private Agenda agenda;
	
	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnModificar().addActionListener(this);
	}
	
	public void inicializar() {
		this.llenarTabla();
		vista.show();
	}
	
	private void llenarTabla() {
		vista.getModelContactos().setRowCount(0);
		vista.getModelContactos().setColumnCount(0);
		vista.getModelContactos().setColumnIdentifiers(vista.getNombreColumnas());

		personas_en_tabla = agenda.obtenerPersonas();
		
		for (int i = 0; i < personas_en_tabla.size(); i ++) {
			Object[] fila = {
					personas_en_tabla.get(i).getNombre(),
					personas_en_tabla.get(i).getTelefono(),
					personas_en_tabla.get(i).getEmail(),
					personas_en_tabla.get(i).getCalle(),
					personas_en_tabla.get(i).getNumero(),
					personas_en_tabla.get(i).getPiso(),
					personas_en_tabla.get(i).getDepto(),
					personas_en_tabla.get(i).getLocalidad_id().toString(),
					personas_en_tabla.get(i).getFecha_nacimiento().toString(),
					personas_en_tabla.get(i).getTipo_contacto_id().toString()
					};
			this.vista.getModelContactos().addRow(fila);		
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// boton agregar contacto de la vista principal
		if(e.getSource() == this.vista.getBtnAgregar())	{
			this.ventanaContactoAgregar = new VentanaContactoAgregar(this);
		}
		
		// boton borrar contacto de la vista principal	
		else if(e.getSource() == vista.getBtnBorrar()) {
			int[] filas_seleccionadas = vista.getTablaContactos().getSelectedRows();
			for (int fila:filas_seleccionadas)
				agenda.borrarPersona(personas_en_tabla.get(fila));
			
			llenarTabla();
		}

		// boton editar contacto de la vista principal	
		else if(e.getSource() == vista.getBtnModificar()) {
			int[] filas_seleccionadas = vista.getTablaContactos().getSelectedRows();
			if (filas_seleccionadas.length>0) {
				PersonaDTO persona = personas_en_tabla.get(0);
				ventanaEditarContacto = new VentanaContactoModificar(this, persona);
			}
		}

		// boton generar reporte de la vista principal
		else if(e.getSource() == vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();				
		}
		
		
		// BOTONES DE LA VISTA PERSONA (AGREGAR CONTACTO)
		if (ventanaContactoAgregar != null) {
		
		// boton agregar contacto de la vista persona
			if(e.getSource() == ventanaContactoAgregar.getBtnAgregarPersona()) {
				PersonaDTO nuevaPersona = new PersonaDTO(
						-1,
						ventanaContactoAgregar.getLocalidad().getLocalidad_id(),
						ventanaContactoAgregar.getTipoContacto().getTipo_contacto_id(),
						ventanaContactoAgregar.getNombre(),
						ventanaContactoAgregar.getTelefono(),
						ventanaContactoAgregar.getMail(),
						ventanaContactoAgregar.getCalle(),
						ventanaContactoAgregar.getNumero(),
						ventanaContactoAgregar.getPiso(),
						ventanaContactoAgregar.getDepto(),
						ventanaContactoAgregar.getFecha()
						);
				agenda.agregarPersona(nuevaPersona);
				llenarTabla();
				ventanaContactoAgregar.dispose();
			}
			
			// boton agregar localidad de la vista persona
			else if(e.getSource() == ventanaContactoAgregar.getBtnAgregarLocalidad()) {
				ventanaLocalidadABM = new VentanaLocalidadABM();
				new ControladorLocalidadABM(ventanaLocalidadABM, agenda);
			}
			
			// boton agregar tipo de contacto de la vista persona
			else if(e.getSource() == ventanaContactoAgregar.getBtnAgregarTipoContacto()) {
				ventanaTipoContactoABM = new VentanaTipoContactoABM();
				new ControladorTipoContactoABM(ventanaTipoContactoABM, agenda);
			}
			
			
		}
		/*
		// BOTONES DE LA VENTANA DE AGREGAR LOCALIDAD
		if (ventanaLocalidad != null) {
			if(e.getSource() == ventanaLocalidad.getBtnAgregarLocalidad()) {
				LocalidadDTO localidad = new LocalidadDTO(-1, ventanaLocalidad.getDescripcion().getText());
				agenda.agregarLocalidad(localidad);
				ventanaPersona.cargarLocalidades();
				ventanaLocalidad.dispose();
			}
		}		
		*/
		// BOTONES DE LA VENTANA DE EDITAR CONTACTO
		if (ventanaEditarContacto != null) {
			if(e.getSource() == ventanaEditarContacto.getBtnEditarContacto()) {
				PersonaDTO contacto = new PersonaDTO(
						ventanaEditarContacto.getPersonaOriginal().getPersona_id(),
						ventanaEditarContacto.getLocalidad().getLocalidad_id(),
						ventanaEditarContacto.getTipoContacto().getTipo_contacto_id(),
						ventanaEditarContacto.getNombre(),
						ventanaEditarContacto.getTelefono(),
						ventanaEditarContacto.getMail(),
						ventanaEditarContacto.getCalle(),
						ventanaEditarContacto.getNumero(),
						ventanaEditarContacto.getPiso(),
						ventanaEditarContacto.getDepto(),
						ventanaEditarContacto.getFecha()
						);
				agenda.modificarPersona(contacto);
				llenarTabla();
				ventanaEditarContacto.dispose();
			}
		}
		
	}
	
}