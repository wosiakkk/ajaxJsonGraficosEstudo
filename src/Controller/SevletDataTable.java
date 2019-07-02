package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/sevletDataTable")
public class SevletDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SevletDataTable() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet sendo chamado pelo datatable");
		
		String json = "{"+
							"\"draw\": 1,"+
							"\"recordsTotal\": 57,"+
							"\"recordsFiltered\": 57,"+
							"\"data\": ["+
								"["+
									"\"Airi\","+
									"\"Satou\","+
									"\"Accountant\","+
									"\"Tokyo\","+
									"\"28th Nov 08\","+
									"\"$162,700\""+
								"],"+
								"["+
									"\"Angelica\","+
									"\"Ramos\","+
									"\"Chief Executive Officer (CEO)\","+
									"\"London\","+
									"\"9th Oct 09\","+
									"\"$1,200,000\""+
								"]"+
							"]"+
						"}";
			    
		
		response.setStatus(200);//resposta completa OK
		response.getWriter().write(json); //JSON  de resposta(escreve a resposta HTTP)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
