package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import model.Usuario;

@WebServlet("/pages/sevletDataTable")
public class SevletDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();
	
	public SevletDataTable() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Usuario> usuarios = daoUsuario.getUsuarios();
			if(!usuarios.isEmpty()) {
				String data= "";
				int totalUsuarios = usuarios.size();
				int index = 1;
				for (Usuario usuario : usuarios) {
					data +="["+
								"\""+usuario.getId()+"\","+
								"\""+usuario.getLogin()+"\","+
								"\""+usuario.getSenha()+"\""+
							"]";
					//lógica para a inserção de virgula no JSON, pois o último registro não conter virgula
					if(index < totalUsuarios) {
						data += ",";
					}
					index ++;
				}
				
				String json = "{"+
									"\"draw\": 1,"+
									"\"recordsTotal\": "+usuarios.size()+","+
									"\"recordsFiltered\": "+usuarios.size()+","+
									"\"data\": ["+data+"]"+
								"}"; 
				response.setStatus(200);//resposta completa OK
				response.getWriter().write(json); //JSON  de resposta(escreve a resposta HTTP)
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
