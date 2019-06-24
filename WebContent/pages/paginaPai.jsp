<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="../webjars/jquery/3.4.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2> Página Pai</h2>
	<input type="button" value="Carregar Página Filha" onclick="carregar()">
	
	<div id="mostrarPaginaFilha"></div>
</body>
<script type="text/javascript">
	function carregar() {
		$("#mostrarPaginaFilha").load('paginaFilha.jsp'); //carrega a página filha
	}

</script>
</html>