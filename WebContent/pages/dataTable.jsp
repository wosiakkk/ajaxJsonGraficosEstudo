<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="../webjars/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<meta charset="ISO-8859-1">
<title>Data Table Jquery</title>
</head>
<body>
	<table id="minhaTabela" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>Login</th>
				<th>Senha</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</body>
<script>
$(document).ready(function() { // faz o processamento na hora em que abre a tela
    $('#minhaTabela').DataTable( {
        "processing": true,
        "serverSide": false,
        "ajax": "sevletDataTable" // URL de retorno dos dados do servidor(resposta do servidor)
    } );
} );
</script>
</html>