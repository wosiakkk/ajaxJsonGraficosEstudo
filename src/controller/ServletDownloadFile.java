package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;

import dao.DaoUsuario;
import model.Usuario;
import service.RelatorioService;

@WebServlet("/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RelatorioService relatorioService = new RelatorioService();
    private DaoUsuario daoUsuario = new DaoUsuario();
	
    public ServletDownloadFile() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ServletContext context = request.getServletContext();
			String tipoExportar = request.getParameter("tipoExportar");
			List<Usuario> usuarios = daoUsuario.getUsuarios();
			
			//necessario a lsita generica para passar por parâmetro
			List dados = new ArrayList();
			dados.add(usuarios);
			
			String fileUrl = relatorioService.gerarRelatorio(dados, new HashMap(), "rel_usuario", "rel_usuario", context);
			
			//construir o caminho completo e absoluto do arquivo
			java.io.File donwloadFile = new java.io.File(fileUrl);
			
			//lendo o arquivo
			FileInputStream inputStream = new FileInputStream(donwloadFile);
			
			//obter o tipo MIME do arquivo
			String mimeType = context.getMimeType(fileUrl);
			if(mimeType == null) {
				//define um tipo binário se mapeamento mime não for encontrado
				mimeType = "application/octet-stream";
			}
			
			//definir os atributos para a resposta
			response.setContentType(mimeType);
			response.setContentLength((int) donwloadFile.length());
			
			//definir o cabeçalho para a resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", donwloadFile.getName());
			response.setHeader(headerKey, headerValue);
			
			//obter um fluxo de saida da resposta
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesReader = -1;
			
			//Escrever bytes lidos a partir do fluxo de entrada para o fluxo de saida
			while((bytesReader = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,bytesReader);
			}
				
			inputStream.close();
			outputStream.close();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
