package persistencia.dao.interfaz;

import java.util.List;

import persistencia.dto.TipoContactoDTO;

public interface TipoContactoDAO {
	
	public boolean insert(TipoContactoDTO tipo);
	
	public boolean delete(TipoContactoDTO tipo);
	
	public boolean update(TipoContactoDTO tipo);
		
	public String selectDescripcion(Integer tipo_contacto_id);
	
	public List<TipoContactoDTO> readAll();
	
}