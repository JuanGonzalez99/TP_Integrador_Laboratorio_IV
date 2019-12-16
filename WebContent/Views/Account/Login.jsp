<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Sistema académico</title>

	<link rel="stylesheet" type="text/css" href="../../Content/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="../../Content/css/util.css">
	<link rel="stylesheet" type="text/css" href="../../Content/css/main.css">
	
	<script src="../../Content/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="../../Content/vendor/animsition/js/animsition.min.js"></script>
	<script src="../../Content/vendor/bootstrap/js/popper.js"></script>
	<script src="../../Content/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../Content/vendor/select2/select2.min.js"></script>
	<script src="../../Content/vendor/daterangepicker/moment.min.js"></script>
	<script src="../../Content/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="../../Content/vendor/countdowntime/countdowntime.js"></script>
	<script src="../../Content/js/main.js"></script>
<!--  
<link href="../../Content/css/Login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" method="post" action="/Juan_Gonzalez_TP_Integrador1/LoginServlet" >
					<span class="login100-form-title p-b-33">
						Login
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Este campo es requerido">
						<input class="input100" type="text" name="txtEmail" placeholder="Email">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Este campo es requerido">
						<input class="input100" type="password" name="txtPassword" placeholder="Contraseña">
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

	<%
	
		if (request.getAttribute("Loggear") != null) {
			Boolean loggear = Boolean.parseBoolean(request.getAttribute("Loggear").toString());
			if (loggear) {
				response.sendRedirect(request.getContextPath() + "/Views/Home/Index.jsp");
			}
		}
	
	%>
	
	
</body>
</html>