package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.dto.PersonaDTO;

public class ReporteAgenda {
	private JasperReport reporte;
	private JasperViewer reporteViewer;	
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);

	// Recibe una lista de personas DTO en cualquier estado
	public ReporteAgenda(List<PersonaDTO> personasDTO) {

		// La convierto en una lista de personas vo reporte
		List<PersonaVOReporte> personas =  ReporteUtil.obtenerPersonasVOReporte(personasDTO);
		
		// Ordeno esta nueva lista por servidor de mail primero y luego por apellido y nombre
		personas = ReporteUtil.ordenarPersonasVOReporte(personas);
		
    	Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	
		try	{
			reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "porMes.jasper" );
			reporteLleno = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(personas));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) {
			log.error("Ocurrió un error mientras se cargaba el archivo porMes.Jasper", ex);
		}
    }       
    
    public void mostrar() {
		reporteViewer = new JasperViewer(reporteLleno, false);
		reporteViewer.setVisible(true);
	}

}