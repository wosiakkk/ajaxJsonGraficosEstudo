package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import model.Usuario;

public class DaoUsuario {
	
	private static Connection connection;
	//criando a conexão ao construir a classe
	public DaoUsuario() {
		connection = ConnectionDataBase.getConnection();
	}
	
	public List<Usuario> getUsuarios() throws SQLException{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuarios";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Usuario user = new Usuario();
			user.setLogin(rs.getString("login"));
			user.setId(String.valueOf(rs.getInt("id")));
			user.setSenha(rs.getString("senha"));
			usuarios.add(user);
		}
		return usuarios;
	}
	
	
	
}
