<%@page import="dominio.Curso"%>
<%@page import="dao.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%
try {
	int idCurso = Integer.parseInt(request.getParameter("idCurso"));
	CursoDAO dao = new CursoDAO();
	Curso curso = dao.GetById(idCurso);

	request.setAttribute("curso", curso);
	request.setAttribute("idCurso", idCurso);
} catch (Exception e) {
	response.setHeader("Location", request.getContextPath() + "/Admin/Cursos");
	response.setStatus(302);
}
%>

<t:userpage title="Alumnos por curso">

	<input type="hidden" id="idCurso" value="${ idCurso }">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Alumnos por curso
        <small>Listado y ABM</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="../Account/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
        <li class="active">Alumnos por curso</li>
      </ol>
    </section>
    
    <section class="content">
    
    
		<div class="box box-primary direct-chat direct-chat-primary box-shadow">
		    <div class="box-header with-border">
		        <h3 class="box-title">
		            Lista de alumnos de ${ curso }
		        </h3>
		    </div>
		    <div class="box-body">
		        <div class="col-md-12 box-header with-border">
			        <div style="margin-bottom: 20px;">
			            <button type="button" id="btnAdd" class="btn btn-info">
			                Inscribir alumno
			            </button>
			        </div>
		            <table class="table table-striped table-bordered dataTable" id="datatable" style="width:100%">
		                <thead>
		                    <tr>
		                    	<th class="id hidden not-editable number" data-prop="id"></th>
								<th class="id hidden" data-prop="idAlumno"></th>
								<th class="select alumno">Alumno</th>
								<th class="actions">Acciones</th>
		                    </tr>
		                </thead>
						<tbody>
						</tbody>
		            </table>
		        </div>
		    </div>
		    <div id="tableLoader" class="overlay">
              <i class="fa fa-refresh fa-spin"></i>
            </div>
		</div>
    
    </section>


</t:userpage>

<script src="../../Content/js/datatable.js"></script>


<script>

	var idCurso = $('#idCurso').val();

	$(function () {
		
		$.ajax({
			type: "GET",
			url: "../../servletCursoAlumno",
			data: { idCurso: idCurso},
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
			var alumno = this.alumno.nombre + " " + this.alumno.apellido;
			
			data = [];
			data.push(this.id);
			data.push(this.idAlumno);
			data.push(alumno);
			data.push(btnDelete);
			
			oTable.fnAddData(data);

		});
		
		oTable.fnDraw();

		triggerTableLoader(false);
	}
	
	function insertOrUpdate(data) {
		
		data["idCurso"] = idCurso;
		
		var result = -1;

		$.ajax({
			url: "../../servletCursoAlumno",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(data),
			async: false,
			success: function (data) {
				if (data.Result) {
					result = data.Id || 0;
					MessageBox.showSuccess("Inscripción guardada correctamente");
				} else if (data.Info) {
					MessageBox.showInfo(data.Info);
				} else {
					var errorMessage = "Ha ocurrido un error al guardar la inscripción, intente nuevamente en unos minutos";
					if (data.Error) {
						errorMessage = data.Error;
					}
					MessageBox.showError(errorMessage);
				}		
			},
			error: ajaxErrorShow
		});
		
		return result;
	}
	
	function deleteObj(id) {
		return false;
	}
	
</script>