package agenda.persistencia.pojos;

import java.sql.Date;

public class PersonaDTO {
	private Integer persona_id, localidad_id, tipo_contacto_id;
	private String nombre, apellido, telefono, email, calle, numero, piso, depto;
	private Date cumple;

	public PersonaDTO(Integer persona_id, Integer localidad_id, Integer tipo_contacto_id, String nombre,
			String apellido, String telefono, String email, String calle, String numero, String piso, String depto,
			Date cumple) {
		this.persona_id = persona_id;
		this.localidad_id = localidad_id;
		this.tipo_contacto_id = tipo_contacto_id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.depto = depto;
		this.cumple = cumple;
	}

	public Integer getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(Integer persona_id) {
		this.persona_id = persona_id;
	}

	public Integer getLocalidad_id() {
		return localidad_id;
	}

	public void setLocalidad_id(Integer localidad_id) {
		this.localidad_id = localidad_id;
	}

	public Integer getTipo_contacto_id() {
		return tipo_contacto_id;
	}

	public void setTipo_contacto_id(Integer tipo_contacto_id) {
		this.tipo_contacto_id = tipo_contacto_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public Date getCumple() {
		return cumple;
	}

	public void setCumple(Date cumple) {
		this.cumple = cumple;
	}
	
}