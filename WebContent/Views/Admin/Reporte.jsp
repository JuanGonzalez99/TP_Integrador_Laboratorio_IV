<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminpage title="Reporte">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reporte
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${ mainPath }/Admin/Index.jsp"><i class="fa fa-dashboard"></i>Inicio</a></li>
        <li class="active">Reporte</li>
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
			        <div class="row">
			        	<div class="form-group">
			        		<div class="col-md-8">
			        			<label>Materia</label>
			        			<select id="materia" class="form-control"></select>
			        		</div>
			        	</div>
			        </div>
			        <div class="row">
				        <div class="form-group">
				        	<div class="col-md-4">
				        		<label>Desde</label>
				        		<input type="number" id="desde" class="form-control" />
				        	</div>
				        	<div class="col-md-4">
				        		<label>Hasta</label>
				        		<input type="number" id="hasta" class="form-control" />
				        	</div>
				        </div>
			        </div>
					<button type="button" id="search" class="btn btn-info" style="margin:20px 0;">Buscar</button>
		            <table class="table table-striped table-bordered dataTable" id="datatable" style="width: 100%">
		                <thead>
		                    <tr>
		                   		<th>Curso</th>
		                   		<th>Alumnos regularizados</th>
		                   		<th>Alumnos libres</th>
		                   		<th>Alumnos totales</th>
		                   		<th>% alumnos regularizados</th>
		                   		<th>% alumnos libres</th>
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

<script src="${ contentPath }/js/Reporte.js"></script>