package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.PersonaDTO;
import modelo.Agenda;
import modelo.PersonaReporte;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteAgenda {
	private JasperReport reporte;
	
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);

	private JasperViewer reporteViewer;
	
	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas) {
    	//Hardcodeado
    	
    	
    	Collections.sort(personas,new Comparator<PersonaDTO>() {
    		
    @Override
    		public int compare(PersonaDTO p1, PersonaDTO p2) {
    	  	
    	return new Integer (p1.getCumple().compareTo(p2.getCumple()));
    		}
    		
    	});
	
    	
    	List<PersonaReporte> personasReporte =  new ArrayList<PersonaReporte>();
    	
    	personasReporte.addAll(PersonaReporte.personasServidor(personas)); 
    System.out.println(personasReporte.toString() + "acaaaa");
    	
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try	{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "porMes.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(personasReporte));
    		log.info("Se cargó correctamente el reporte");
		}
    	
		catch( JRException ex ) {
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
    }       
    
    public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}