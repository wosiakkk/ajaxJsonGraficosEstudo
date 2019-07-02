<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="../webjars/jquery/3.4.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<title>Upload de arquivos</title>
</head>
<body>
	<h2>Upload de arquivos</h2>
	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" id="target" width="200px" height="200px" src="">
</body>
<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;
			

			//upload Ajax

			$.ajax({
				method : "POST",
				url : "fileUpload",
				data : {
					fileUpload : reader.result
				}
			}).done(function(response) {
				alert("Sucesso: " + response);
			}).fail(function(xhr, status, errorThrown) {
				alert("Error: " + xhr.responseText);
			});
		};

		if (file) {
			reader.readAsDataURL(file);

		} else {
			target.src = "";
		}
	}
</script>
</html>