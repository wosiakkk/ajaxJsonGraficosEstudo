package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import User.UserLogado;

// delimita qual p�gina o filtro ndeve interceptar, se for '/*' todas as p�ginas do sistema ser�o interceptadas
//no caso estmaos capturando para todas p�ginas dentro do diret�rio pages
@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {

	//faz alguma coisa quando a aplica��o � derrubada
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	
	//intercepta todas as requisi��es 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//capturando se o usu�rio est� logado
		//transformando o objeto de classe abstrata request em um obj http
		HttpServletRequest req = (HttpServletRequest) request;
		//buscando o obj usuario na sess�o
		HttpSession session = req.getSession();
		
		//Pega o caminho para saber uqal p�gina do diret�rio pages o filter est� interceptando
		String urlParaAutenticar = req.getServletPath();
		
		UserLogado user = (UserLogado) session.getAttribute("usuario");
		
		//usuario n�o logado e url para autenticar � difenrete da tela de login
		if(user == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/autenticar.jsp?url="+urlParaAutenticar);
			dispatcher.forward(request, response);
			return; // da retorno para o processo redirecionar
		}
		
		//usu�rio autenticado
		// o chain � respons�vel por executar as a��es do request e response
		chain.doFilter(request, response);
		
	}
	
	//executa alguma coisa quando a aaplica��o � iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

}
