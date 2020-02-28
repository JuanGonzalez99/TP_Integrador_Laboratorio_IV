<%@page import="dominio.Curso"%>
<%@page import="dao.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%
try {

	String param = request.getParameter("idCurso");
	if (param == null || param.isEmpty())
	{
		throw new Exception();
	}
	int idCurso = Integer.parseInt(param);
	CursoDAO dao = new CursoDAO();
	Curso curso = dao.GetById(idCurso);
	
	request.setAttribute("curso", curso);
	request.setAttribute("idCurso", idCurso);
} catch (Exception ex) {	
	response.setHeader("Location", request.getContextPath() + "/Login");
	response.setStatus(302);
}
%>

<t:teacherpage title="Alumnos por curso">

	<input type="hidden" id="idCurso" value="${ idCurso }">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Alumnos por curso
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ mainPath }/Profesor/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
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
			            <button type="button" id="btnCalificate" class="btn btn-info">
			                Calificar
			            </button>
			        </div>
			        <div class="hidden" style="margin-bottom: 20px;">
			            <button type="button" id="btnSave" class="btn btn-info">
			                Guardar
			            </button>
			        </div>
		            <table class="table table-striped table-bordered dataTable" id="datatable" style="width:100%">
		                <thead>
		                    <tr>
		                    	<th class="id hidden not-editable number" data-prop="id"></th>
								<th class="not-editable" data-prop="idAlumno">Legajo</th>
								<th class="not-editable">Nombre</th>
								<th class="not-editable">Apellido</th>
								<th class="not-editable">Email</th>
								<th class="nota" data-prop="parcial1">Parcial 1</th>
								<th class="nota" data-prop="parcial2">Parcial 2</th>
								<th class="nota" data-prop="recuperatorio1">Recuperatorio 1</th>
								<th class="nota" data-prop="recuperatorio2">Recuperatorio 2</th>
								<th class="id hidden" data-prop="idEstado"></th>
								<th class="select estado">Estado</th>
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

</t:teacherpage>

<script src="${ contentPath }/js/datatable.js"></script>


<script>

	var idCurso = $('#idCurso').val();

	$(function () {
		
		triggerTableLoader(true);
		
		$.ajax({
			type: "GET",
			url: mainPath + "/servletCursoAlumno",
			data: { idCurso: idCurso},
			success: fillTable,
			error: ajaxErrorShow
		});
		
	});
	
	function fillTable(list) {
		
		var oTable = $('#datatable').dataTable();

		if(typeof list == "string" || list[0] == null) {
			triggerTableLoader(false);
			return;
		}
		
		$.each(list, function () {
			
			data = [];
			data.push(this.id);
			data.push(this.idAlumno);
			data.push(this.alumno.nombre);
			data.push(this.alumno.apellido);
			data.push(this.alumno.email);
			data.push(parseNota(this.parcial1));
			data.push(parseNota(this.parcial2));
			data.push(parseNota(this.recuperatorio1));
			data.push(parseNota(this.recuperatorio2));
			data.push(this.idEstado || "");
			data.push(this.estado.descripcion || "");

			oTable.fnAddData(data);

		});
		
		oTable.fnDraw();

		triggerTableLoader(false);
	}
	
	function parseNota(nota) {
		if (nota == 0) {
			return "";
		}
		return nota.toString();
	}
	
	function deparseNota(nota) {
		if (nota == null || nota.trim() == "") {
			return 0;
		}			
		return parseInt(nota);
	}
	
	function insertOrUpdate(data) {
		
		data["idCurso"] = idCurso;
		
		var result = -1;

		$.ajax({
			url: mainPath + "/servletCursoAlumno",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(data),
			async: false,
			success: function (data) {
				if (data.Result) {
					result = data.Id || 0;
					MessageBox.showSuccess("Notas guardadas correctamente");
				} else if (data.Info) {
					MessageBox.showInfo(data.Info);
				} else {
					var errorMessage = "Ha ocurrido un error al guardar el curso, intente nuevamente en unos minutos";
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