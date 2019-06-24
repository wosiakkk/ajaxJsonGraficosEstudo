package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import User.UserLogado;


@WebServlet("/pages/ServletAutenticacao")
public class ServletAutenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletAutenticacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean deslogar = Boolean.parseBoolean(request.getParameter("deslogar"));
		if(deslogar) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("../index.jsp");		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		//neste momento pode ser feita uma validação no BD
		if(login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
			//se o login der certo
			UserLogado user = new UserLogado();
			user.setLogin(login);
			user.setSenha(senha);
			HttpSession session = request.getSession();
			session.setAttribute("usuario", user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			return; // da retorno para o processo redirecionar
		}else {
			//redireciona a página de login novamente
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/autenticar.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
