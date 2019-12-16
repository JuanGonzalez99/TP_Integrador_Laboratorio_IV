<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" required="true"%>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="utf-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
	 
	 <title>${ title } - Sistema academico</title>
	 
	 <link rel="shortcut icon" href="/Juan_Gonzalez_TP_Integrador1/logo_utn.ico">

	 <!-- Tell the browser to be responsive to screen width -->
	 <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	 
	 <!-- STYLES -->
	 
	 <!-- Bootstrap 3.3.7 -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
	 <!-- Font Awesome -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
	 <!-- Ionicons -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
	 <!-- Theme style -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/dist/css/AdminLTE.min.css">
	 <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/dist/css/skins/_all-skins.min.css">
	 <!-- Morris chart -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/morris.js/morris.css">
	 <!-- jvectormap -->
	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/jvectormap/jquery-jvectormap.css">
	 <!-- Date Picker -->
<!-- 	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"> -->
	 <!-- Daterange picker -->
<!-- 	 <link rel="stylesheet" href="../../Content/AdminLTE/bower_components/bootstrap-daterangepicker/daterangepicker.css"> -->
	 <!-- bootstrap wysihtml5 - text editor -->
<!-- 	 <link rel="stylesheet" href="../../Content/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"> -->

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	
	<!-- DATATABLE -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
	
	<!-- SELECT 2.0 -->
	<link rel="stylesheet" type="text/css" href="../../Content/css/select2.min.css">
	
	<!-- DATEPICKER -->
	<link rel="stylesheet" type="text/css" href="../../Content/css/bootstrap-datepicker.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/css/bootstrap-datepicker3.min.css">
	<link rel="stylesheet" type="text/css" href="../../Content/css/bootstrap-datepicker.standalone.min.css">


	<link rel="stylesheet" href="../../Content/css/personal.css" >
	
	 <!-- END STYLES -->


	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<!-- Google Font -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
	
	

</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">

		<jsp:invoke fragment="header"/>
		
		<jsp:doBody/>
		
		<jsp:invoke fragment="footer"/>
      
	</div>

</body>

<!-- SCRIPTS -->

<!-- jQuery 3 -->
<script src="../../Content/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
<!--     <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->

<!-- jQuery UI 1.11.4 -->
<script src="../../Content/AdminLTE/bower_components/jquery-ui/jquery-ui.min.js"></script>

<!-- DATATABLE -->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.20/sorting/datetime-moment.js"></script>

<!-- SELECT 2.0 -->
<script src="../../Content/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/i18n/es.js"></script>



<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="../../Content/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="../../Content/AdminLTE/bower_components/raphael/raphael.min.js"></script>
<script src="../../Content/AdminLTE/bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="../../Content/AdminLTE/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="../../Content/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../../Content/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="../../Content/AdminLTE/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="../../Content/AdminLTE/bower_components/moment/min/moment.min.js"></script>
<script src="../../Content/AdminLTE/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<!-- <script src="../../Content/AdminLTE/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script> -->
<!-- Bootstrap WYSIHTML5 -->
<script src="../../Content/AdminLTE/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="../../Content/AdminLTE/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../Content/AdminLTE/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../Content/AdminLTE/dist/js/adminlte.min.js"></script>

<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- <script src="../../Content/AdminLTE/dist/js/pages/dashboard.js"></script> -->
<!-- AdminLTE for demo purposes -->
<!-- <script src="../../Content/AdminLTE/dist/js/demo.js"></script> -->


<!-- DATEPICKER -->
<script src="../../Content/vendor/bootstrap/js/popper.min.js"></script>
<script src="../../Content/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../../Content/js/bootstrap-datepicker.min.js"></script>
<script src="../../Content/js/bootstrap-datepicker.es.min.js"></script>

<!-- SWAL2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="../../Content/js/MessageBox.js"></script>

<!-- END SCRIPTS -->

</body>
</html>