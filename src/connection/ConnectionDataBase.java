package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class ConnectionDataBase {
	
	private static String banco= "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public ConnectionDataBase() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco,user,password);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar no Banco de Dados" +e);
		}
	}
	/**
	 * retorna a conexão do banco de dados
	 * @return Connection SQL
	 */
	public static Connection getConnection() {
		return connection;
	}
}
