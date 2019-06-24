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

// delimita qual página o filtro ndeve interceptar, se for '/*' todas as páginas do sistema serão interceptadas
//no caso estmaos capturando para todas páginas dentro do diretório pages
@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {

	//faz alguma coisa quando a aplicação é derrubada
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	
	//intercepta todas as requisições 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//capturando se o usuário está logado
		//transformando o objeto de classe abstrata request em um obj http
		HttpServletRequest req = (HttpServletRequest) request;
		//buscando o obj usuario na sessão
		HttpSession session = req.getSession();
		
		//Pega o caminho para saber uqal página do diretório pages o filter está interceptando
		String urlParaAutenticar = req.getServletPath();
		
		UserLogado user = (UserLogado) session.getAttribute("usuario");
		
		//usuario não logado e url para autenticar é difenrete da tela de login
		if(user == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutenticacao")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/autenticar.jsp?url="+urlParaAutenticar);
			dispatcher.forward(request, response);
			return; // da retorno para o processo redirecionar
		}
		
		//usuário autenticado
		// o chain é responsável por executar as ações do request e response
		chain.doFilter(request, response);
		
	}
	
	//executa alguma coisa quando a aaplicação é iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

}
