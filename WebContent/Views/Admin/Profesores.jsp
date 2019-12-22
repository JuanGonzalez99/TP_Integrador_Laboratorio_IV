<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminpage title="Profesores">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Profesores
        <small>Listado y abm</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="../Account/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
        <li class="active">Profesores</li>
      </ol>
    </section>

    <section class="content">
		<div class="box box-primary direct-chat direct-chat-primary box-shadow">
		    <div class="box-header with-border">
		        <h3 class="box-title">
		            Lista de profesores
		        </h3>
		    </div>
		    <div class="box-body">
		        <div class="col-md-12 box-header with-border">
			        <div style="margin-bottom: 20px;">
			            <button type="button" id="btnAdd" class="btn btn-info">
			                Nuevo profesor
			            </button>
			        </div>
		            <table class="table table-striped table-bordered dataTable" id="datatable">
		                <thead>
		                    <tr>
								<th class="row-id not-editable" data-prop="id">Legajo</th>
								<th data-prop="apellido">Apellido</th>
								<th data-prop="nombre">Nombre</th>
								<th class="date" data-prop="fechaNacimiento">Fecha de nacimiento</th>
								<th class="number" data-prop="dni">DNI</th>
								<th data-prop="direccion">Direccion</th>
								<th class="id localidad hidden" data-prop="idLocalidad"></th>
								<th class="select localidad">Localidad</th>
								<th data-prop="email">Email</th>
								<th class="number" data-prop="telefono">Telefono</th>
								<th class="special actions">Acciones</th>
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


</t:adminpage>

<script src="../../Content/js/datatable.js"></script>

<script>

	$(function () {
		
		triggerTableLoader(true);
		
		$.ajax({
			type: "GET",
			url: "../../servletProfesor",
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
			
			var dia = this.fechaNacimiento.day.toString().padStart(2, '0');
			var mes = this.fechaNacimiento.month.toString().padStart(2, '0');
			var anio = this.fechaNacimiento.year;

			data = [];
			data.push(this.id);
			data.push(this.apellido);
			data.push(this.nombre);
			data.push(dia+"/"+mes+"/"+anio);
			data.push(this.dni);
			data.push(this.direccion);
			data.push(this.idLocalidad);
			data.push(this.localidad.nombre);
			data.push(this.email);
			data.push(this.telefono);					
			data.push(btnEdit + ' ' + btnDelete);
			
			oTable.fnAddData(data);
		});
		
		oTable.fnDraw();

		triggerTableLoader(false);
	}
	
	function insertOrUpdate(data) {
		var result = -1;

		$.ajax({
			url: "../../servletProfesor",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(data),
			async: false,
			success: function (data) {
				if (data.Result) {
					result = data.Id || 0;
					MessageBox.showSuccess("Profesor guardado correctamente");
				} else if (data.Info) {
					MessageBox.showInfo(data.Info);
				} else {
					var errorMessage = "Ha ocurrido un error al guardar el profesor, intente nuevamente en unos minutos";
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
		
		var result = false;
		
		$.ajax({
			url: "../../deleteProfesor",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(id),
			async: false,
			success: function (data) {
				if (data.Result) {
					result = true;
					MessageBox.showSuccess('Profesor eliminado exitosamente');
				} else {
					var errorMessage = "Ha ocurrido un error al eliminar el profesor, intente nuevamente en unos minutos";
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
	
</script>