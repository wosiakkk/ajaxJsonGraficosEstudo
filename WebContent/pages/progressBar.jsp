<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="../webjars/jquery/3.4.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Barra de progresso</title>

<style type="text/css">
/*fundo da barra de progresso*/
	#myProgress{
		width: 100%; 
		background-color: gray; 
	}
	/*cor da barra de progresso*/
	#myBar{
		width: 1%;
		height: 30px;
		background-color: green;
	}
</style>

</head>
<body>
	<h2>Barra de progresso</h2>
	<h2>Exemplo com JavaScript</h2>
	<div id="myProgress">
		<div id="myBar">
		</div>
	</div>
	<br/>
	<button onclick="exibirBarra()"> Iniciar barra de progresso</button>
</body>
<!-- com java Script -->
<script type="text/javascript">
	function exibirBarra() {
		var elem = document.getElementById("myBar");
		var width = 1;
		var id = setInterval(frame,10);
		
		function frame() {
			if(width >= 100){
				clearInterval(id);
			}else{
				width++;
				elem.style.width = width + "%";
			}
		}
		
	}
</script>
<!-- com jquery -->
<script type="text/javascript">


</script>
</html>