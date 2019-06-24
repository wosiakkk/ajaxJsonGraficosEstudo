<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="../webjars/jquery/3.4.1/jquery.min.js"></script>
<title>Capturando Exce��es com jquery</title>
</head>
<body>
	<h3>Capturando Exce��es com jquery</h3>
	<input type="text" value="valor informado" id="txtvalor">
	<input type="button" onclick="testarExcecao()" value="Testar Exce��o">
</body>
<!-- Importando jquery da biblioteca de reposit�rio do maven -->


<script type="text/javascript">
	function testarExcecao() {
		valorInformado = $('#txtvalor').val();
		
		$.ajax({
			method: "POST",
			url: "capturarExcecao", // para qual servlet
			data: {valorParam: valorInformado}
		})
			.done(function(response) { //Resposta ok -  nenhum erro
				alert(response);
			//fazer algo se tudo deu certo
			})
		.fail(function(xhr, status, errorThrown) { //Resposta erro -erro ocorreu
			alert("Error: " + xhr.responseText); // mostra a resposta do servidor
		//fazer algo se deu errado
		});
	}
</script>
</html>