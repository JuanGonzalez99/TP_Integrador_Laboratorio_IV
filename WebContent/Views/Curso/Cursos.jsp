<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:userpage userName="Juan Gonzalez" title="Cursos">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Cursos
        <small>Listado y abm</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="../Account/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
        <li class="active">Cursos</li>
      </ol>
    </section>
    
    <section class="content">
    
    	<div class="box box-primary direct-chat direct-chat-primary box-shadow">
		    <div class="box-header with-border">
		        <h3 class="box-title">
		            Lista de cursos
		        </h3>
		    </div>
		    <div class="box-body">
		        <div class="col-md-12 box-header with-border">
			        <div style="margin-bottom: 20px;">
			            <button type="button" id="btnAdd" class="btn btn-info">
			                Nuevo curso
			            </button>
			        </div>
		            <table class="table table-striped table-bordered dataTable cant-edit cant-delete" id="datatable" style="width: 100%">
		                <thead>
		                    <tr>
		                    	<th class="row-id not-editable" data-prop="id">Id</th>
								<th class="id hidden" data-prop="idMateria"></th>
								<th class="select materia">Materia</th>
								<th class="date year" data-prop="anio">Año</th>
								<th class="id hidden" data-prop="idSemestre"></th>
								<th class="select semestre">Semestre</th>
								<th class="id hidden" data-prop="idProfesor"></th>
								<th class="select profesor">Docente</th>
								<th class="detail-alumnos not-editable">Alumnos</th>
								<th class="actions">Acciones</th>
		                    </tr>
		                </thead>
						<tbody>
							<!--<tr>
								<td>Laboratorio de Computación IV</td>
								<td>0</td>
								<td>Segundo</td>
								<td>0</td>
								<td>2019</td>
								<td>Tamara Herrera</td>
								<td>0</td>
								<td><a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Curso/AlumnosCurso.jsp" class="btn btn-link alumnos">Lista de Alumnos</a></td>
								<td>
									<button data-toggle="tooltip" data-container="body" title="Editar" class="btn btn-sm btn-flat btn-success edit"><i class="fa fa-pencil"></i></button>
									<button data-toggle="tooltip" data-container="body" title="Eliminar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button>
								</td>
							</tr>
							<tr>
								<td>Programación III</td>
								<td>0</td>
								<td>Primero</td>
								<td>0</td>
								<td>2019</td>
								<td>Maximiliano Sar Fernandez</td>
								<td>0</td>
								<td><a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Curso/AlumnosCurso.jsp" class="btn btn-link alumnos">Lista de Alumnos</a></td>
								<td>
									<button data-toggle="tooltip" data-container="body" title="Editar" class="btn btn-sm btn-flat btn-success edit"><i class="fa fa-pencil"></i></button>
									<button data-toggle="tooltip" data-container="body" title="Eliminar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button>
								</td>
							</tr>
							<tr>
								<td>Programación II</td>
								<td>0</td>
								<td>Segundo</td>
								<td>0</td>
								<td>2018</td>
								<td>Daniel Kloster</td>
								<td>0</td>
								<td><a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Curso/AlumnosCurso.jsp" class="btn btn-link alumnos">Lista de Alumnos</a></td>
								<td>
									<button data-toggle="tooltip" data-container="body" title="Editar" class="btn btn-sm btn-flat btn-success edit"><i class="fa fa-pencil"></i></button>
									<button data-toggle="tooltip" data-container="body" title="Eliminar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button>
								</td>
							</tr>-->
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

	$(function () {
		
		triggerTableLoader(true);
		
		$.ajax({
			type: "GET",
			url: "../../servletCurso",
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
			var profesor = this.profesor.apellido + ", " + this.profesor.nombre;

			data = [];
			data.push(this.id);
			data.push(this.idMateria);
			data.push(this.materia.nombre);
			data.push(this.anio);
			data.push(this.idSemestre);
			data.push(this.semestre.descripcion);
			data.push(this.idProfesor);
			data.push(profesor);
			data.push(btnAlumnos);
			data.push(btnEdit);
			
			oTable.fnAddData(data);
		});
		
		oTable.fnDraw();

		triggerTableLoader(false);
	}
	
	function insertOrUpdate(data) {
		var result = -1;

		$.ajax({
			url: "../../servletCurso",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(data),
			async: false,
			success: function (data) {
				if (data.Result) {
					result = data.Id || 0;
					MessageBox.showSuccess("Curso guardado correctamente");
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