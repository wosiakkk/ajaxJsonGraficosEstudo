<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Autenticar Uusário</h2>

	<form action="ServletAutenticacao" method="post">
		<input value="<%= request.getParameter("url") %>" id="url" name="url"
			 hidden="true">
		<table>
			<tr>
				<th>Login</th>
				<th>Senha</th>
			</tr>
			<tr>
				<td><input type="text" id="login" name="login"></td>
				<td><input type="password" id="senha" name="senha"></td>
			</tr>
			<tr>
				<td><input type="submit" id="logar" name="logar" value="Logar"></td>
			</tr>
		</table>
	</form>

</body>
</html>