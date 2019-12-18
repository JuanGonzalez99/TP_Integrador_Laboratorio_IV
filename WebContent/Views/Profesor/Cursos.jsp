<%@page import="dominio.Profesor"%>
<%@page import="dao.ProfesorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%
	int idProfesor = 1;
	ProfesorDAO dao = new ProfesorDAO();
	Profesor profesor = dao.GetById(idProfesor);
	
	request.setAttribute("idProfesor", idProfesor);
	request.setAttribute("profesor", profesor);
%>

<t:teacherpage title="Cursos por profesor">

	<input type="hidden" id="idProfesor" value="${ idProfesor }" />

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Cursos por profesor
<!--         <small>Control panel</small> -->
      </h1>
      <ol class="breadcrumb">
        <li><a href="../Account/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
        <li class="active">Cursos por profesor</li>
      </ol>
    </section>
    
    <section class="content">
    
    	<div class="box box-primary direct-chat direct-chat-primary box-shadow">
		    <div class="box-header with-border">
		        <h3 class="box-title">
		            Lista de cursos de ${ profesor }
		        </h3>
		    </div>
		    <div class="box-body">
		        <div class="col-md-12 box-header with-border">
<!-- 			        <div style="margin-bottom: 20px;"> -->
<!-- 			            <button type="button" id="btnAdd" class="btn btn-info"> -->
<!-- 			                Nuevo curso -->
<!-- 			            </button> -->
<!-- 			        </div> -->
		            <table class="table table-striped table-bordered dataTable" id="datatable" style="width: 100%">
		                <thead>
		                    <tr>
								<th>Materia</th>
								<th>Semestre</th>
								<th>Año</th>
								<th class="special detail alumnos">Alumnos</th>
<%-- 								<th class="special actions">Acciones</th> --%>
		                    </tr>
		                </thead>
						<tbody>
							<tr>
								<td>Laboratorio de Computacion IV</td>
								<td>Segundo</td>
								<td>2019</td>
								<td><a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Profesor/AlumnosProfesor.jsp" class="btn btn-link alumnos">Lista de Alumnos</a></td>
<%-- 								<td> --%>
<!-- 									<button data-toggle="tooltip" data-container="body" title="Editar" class="btn btn-sm btn-flat btn-success edit"><i class="fa fa-pencil"></i></button> -->
<!-- 									<button data-toggle="tooltip" data-container="body" title="Eliminar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button> -->
<%-- 								</td> --%>
							</tr>
						</tbody>
		            </table>
		        </div>
		    </div>
		</div>
		
    </section>

</t:teacherpage>

<script src="../../Content/js/datatable.js"></script>

<script>
	var idProfesor = $('#idProfesor').val();
	$(function () {
		$.ajax({
			url: '../../servletCurso',
			type: 'GET',
			data: idProfesor,
			success: fillTable,
			error: ajaxErrorShow
		});
		
	});
	
	function fillTable(list) {
		if(typeof list == "string" || list[0] == null) {
			triggerTableLoader(false);
			return;
		}
		
		var oTable = $('#datatable').dataTable();
		
		$.each(list, function () {
// 			var idSemestre = this.idEstado;
// 			var semestre = idSemestre == 1 ? "Primer semestre" : (idSemestre == 2 ? "Segundo semestre" : "");
			
			data = [];
			data.push(this.materia.nombre);
			data.push(this.semestre.descripcion);
			data.push(this.anio);
			data.push('<a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Profesor/AlumnosCurso.jsp?idCurso='+ this.id +'" class="btn btn-link alumnos">Lista de Alumnos</a>');
			
			oTable.fnAddData(data);
			
		});
		
		oTable.fnDraw();
		triggerTableLoader(false);
	}
</script>
