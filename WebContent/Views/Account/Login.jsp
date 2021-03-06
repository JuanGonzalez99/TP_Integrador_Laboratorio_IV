<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


	<%
		String mainPath = request.getContextPath();
		request.setAttribute("mainPath", mainPath);
		request.setAttribute("contentPath", mainPath + "/Content");
	%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Sistema acad�mico</title>

	<link rel="shortcut icon" href="<%= request.getContextPath() %>/logo_utn.ico">

	<link rel="stylesheet" type="text/css" href="${ contentPath }/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/css/util.css">
	<link rel="stylesheet" type="text/css" href="${ contentPath }/css/main.css">

	<script src="${ contentPath }/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="${ contentPath }/vendor/animsition/js/animsition.min.js"></script>
	<script src="${ contentPath }/vendor/bootstrap/js/popper.js"></script>
	<script src="${ contentPath }/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ contentPath }/vendor/select2/select2.min.js"></script>
	<script src="${ contentPath }/vendor/daterangepicker/moment.min.js"></script>
	<script src="${ contentPath }/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="${ contentPath }/vendor/countdowntime/countdowntime.js"></script>
	<script src="${ contentPath }/js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script src="${ contentPath }/js/MessageBox.js"></script>
<!--  
<link href="${ contentPath }/css/Login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
-->
</head>
<body>
	<%
 		session.invalidate();
		if (request.getAttribute("loginError") != null) {
			String error = "Ha ocurrido un error inesperado";
			int loginError = Integer.parseInt(request.getAttribute("loginError").toString());
			if (loginError == 1) {
				error = "Usuario o contrase�a incorrectos";
			}
			%>
			<input type="hidden" value="<%= error %>" id="error" />
			<script>
				var error = $('#error').val();
				MessageBox.showError(error);
			</script>
			<%
		}
	
	%>


	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" method="post" action="/Juan_Gonzalez_TP_Integrador1/Login" >
					<span class="login100-form-title p-b-33">
						Login
					</span>
					<div class="wrap-input100 validate-input" data-validate = "Este campo es requerido">
						<input class="input100" type="email" name="txtEmail" placeholder="Email">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Este campo es requerido">
						<input class="input100" type="password" name="txtPassword" placeholder="Contrase�a">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="container-login100-form-btn m-t-20">
						<button class="login100-form-btn" name="btnIngresar">
							Ingresar
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>