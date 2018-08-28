package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
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
	private VentanaContactoModificar ventanaModificarContacto;
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
		SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM", new Locale("ES", "MX"));
		for (int i = 0; i < personas_en_tabla.size(); i ++) {
			Object[] fila = {
					personas_en_tabla.get(i).getNombre(),
					personas_en_tabla.get(i).getTelefono(),
					personas_en_tabla.get(i).getEmail(),
					personas_en_tabla.get(i).getCalle(),
					personas_en_tabla.get(i).getNumero(),
					personas_en_tabla.get(i).getPiso(),
					personas_en_tabla.get(i).getDepto(),
					agenda.obtenerDescripcionDeLocalidad(personas_en_tabla.get(i).getLocalidad_id()),
					formato.format(personas_en_tabla.get(i).getCumple()),
					agenda.obtenerDescripcionDeTipoDeContacto(personas_en_tabla.get(i).getTipo_contacto_id())
					};
			this.vista.getModelContactos().addRow(fila);		
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// ************************************  BOTONES DE LA VISTA ******************************** 
		
		// AGREGAR CONTACTO
		if(e.getSource() == this.vista.getBtnAgregar())	{
			this.ventanaContactoAgregar = new VentanaContactoAgregar(this);
		}
		
		// BORRAR CONTACTO	
		else if(e.getSource() == vista.getBtnBorrar()) {
			int[] filas_seleccionadas = vista.getTablaContactos().getSelectedRows();
			for (int fila:filas_seleccionadas)
				agenda.borrarPersona(personas_en_tabla.get(fila));
			
			llenarTabla();
		}

		// MODIFICAR CONTACTO	
		else if(e.getSource() == vista.getBtnModificar()) {
			int[] filas_seleccionadas = vista.getTablaContactos().getSelectedRows();
			if (filas_seleccionadas.length>0) {
				PersonaDTO persona = personas_en_tabla.get(filas_seleccionadas[0]);
				ventanaModificarContacto = new VentanaContactoModificar(this, persona);
			}
		}

		// MOSTRAR REPORTE
		else if(e.getSource() == vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();				
		}
		
		
		// ************************************  BOTONES DE LA VENTANA AGREGAR CONTACTO ******************************** 
		if (ventanaContactoAgregar != null) {
			// AGREGAR CONTACTO
			if(e.getSource() == ventanaContactoAgregar.getBtnAgregarContacto() && ventanaContactoAgregar.validarInputs()) {
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
			
			// ADMINISTRAR LOCALIDAD
			else if(e.getSource() == ventanaContactoAgregar.getBtnLocalidadABM()) {
				ventanaLocalidadABM = new VentanaLocalidadABM();
				new ControladorLocalidadABM(ventanaLocalidadABM, ventanaContactoAgregar, agenda);
			}
			
			// ADMINISTRAR TIPO DE CONTACTO
			else if(e.getSource() == ventanaContactoAgregar.getBtnTipoContactoABM()) {
				ventanaTipoContactoABM = new VentanaTipoContactoABM();
				new ControladorTipoContactoABM(ventanaTipoContactoABM, ventanaContactoAgregar, agenda);
			}
		}

		// ************************************  BOTONES DE LA VENTANA MODIFICAR CONTACTO ********************************
		if (ventanaModificarContacto != null) {
			// MODIFICAR CONTACTO
			if(e.getSource() == ventanaModificarContacto.getBtnModificarContacto() && ventanaModificarContacto.validarInputs()) {
				PersonaDTO contacto = new PersonaDTO(
						ventanaModificarContacto.getContactoOriginal().getPersona_id(),
						ventanaModificarContacto.getLocalidad().getLocalidad_id(),
						ventanaModificarContacto.getTipoContacto().getTipo_contacto_id(),
						ventanaModificarContacto.getNombre(),
						ventanaModificarContacto.getTelefono(),
						ventanaModificarContacto.getMail(),
						ventanaModificarContacto.getCalle(),
						ventanaModificarContacto.getNumero(),
						ventanaModificarContacto.getPiso(),
						ventanaModificarContacto.getDepto(),
						ventanaModificarContacto.getFecha()
						);
				agenda.modificarPersona(contacto);
				llenarTabla();
				ventanaModificarContacto.dispose();
			}
			
			// ADMINISTRAR LOCALIDAD
			else if(e.getSource() == ventanaModificarContacto.getBtnLocalidadABM()) {
				ventanaLocalidadABM = new VentanaLocalidadABM();
				new ControladorLocalidadABM(ventanaLocalidadABM, ventanaModificarContacto, agenda);
			}
			
			// ADMINISTRAR TIPO DE CONTACTO
			else if(e.getSource() == ventanaModificarContacto.getBtnTipoContactoABM()) {
				ventanaTipoContactoABM = new VentanaTipoContactoABM();
				new ControladorTipoContactoABM(ventanaTipoContactoABM, ventanaModificarContacto, agenda);
			}
		}

	}
	
}