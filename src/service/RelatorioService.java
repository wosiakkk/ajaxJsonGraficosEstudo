package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS =  "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado =  null;
	
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio
			, String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
		//Cria a lista de CollectionDataSource de benas que carregam dados para o relatório
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		//Fornece o caminho fisico até a pasta que contem os relatórios .jasper
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		//Montar o arquivo que via representar o relatório .jasper
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper +  ".jasper");
		
		//verificação, pois a estrutura de pastas muda ao executar o projeto através de um WAR
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()) {
			
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		//caminho para imagens
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//Caminho completo até o relatório compilado indicado
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		//Faz o carregamento do relatório
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		//seta os parâmetros SUBREPORT_DIR com o caminho físico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		//carregar o arquivo compilado para a memória
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		//definindo o tipo de exportação
		exporter = new JRPdfExporter();
		
		//caminho relatório exportado
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
		
		//criar novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//preprar para a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		//nome de saida para o relatório
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		
		//executa a exportação
		exporter.exportReport();
		
		//para não ficar ocupando espaço no servidor, remove o arquivo do servidor após o donwload
		arquivoGerado.deleteOnExit();
		
		
		return caminhoArquivoRelatorio;
	}
	
}
