<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:userpage userName="Juan Gonzalez" title="Alumnos">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Alumnos
        <small>Listado y abm</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="../Test/empty.jsp"><i class="fa fa-dashboard"></i>Index</a></li>
        <li class="active">Alumnos</li>
      </ol>
    </section>

    <section class="content">
		<div class="box box-primary direct-chat direct-chat-primary box-shadow">
		    <div class="box-header with-border">
		        <h3 class="box-title">
		            Lista de alumnos
		        </h3>
		    </div>
		    <div class="box-body">
		        <div class="col-md-12 box-header with-border">
			        <div style="margin-bottom: 20px;">
			            <button type="button" id="btnAdd" class="btn btn-info">
			                Nuevo alumno
			            </button>
			        </div>
		            <table class="table table-striped table-bordered dataTable" id="datatable">
		                <thead>
		                    <tr>
								<th>Legajo</th>
								<th>Apellido</th>
								<th>Nombre</th>
								<th class="date">Fecha nacimiento</th>
								<th>Direccion</th>
								<th class="select provincia">Provincia</th>
								<th class="id provincia"></th>
								<th class="select localidad">Localidad</th>
								<th class="id localidad"></th>
								<th>Email</th>
								<th>Telefono</th>
								<th class="edit">Edición</th>
								<th class="delete">Borrado</th>
		                    </tr>
		                </thead>
						<tbody>
							<tr>
								<td>0001</td>
								<td>Perez</td>
								<td>Mario</td>
								<td>09/03/2001</td>
								<td>Avenida 1234</td>
								<td>Buenos Aires</td>
								<td>0</td>
								<td>San Miguel</td>
								<td>0</td>
								<td>mario@alumno.com</td>
								<td>1512345678</td>
								<td><button class="btn btn-link edit">Editar</button></td>
								<td><button class="btn btn-link delete">Borrar</button></td>
							</tr>
							<tr>
								<td>0002</td>
								<td>Gomez</td>
								<td>Lucia</td>
								<td>17/08/1998</td>
								<td>Calle 234</td>
								<td>Buenos Aires</td>
								<td>0</td>
								<td>San Miguel</td>
								<td>0</td>
								<td>lucia@alumno.com</td>
								<td>1556781234</td>
								<td><button class="btn btn-link edit">Editar</button></td>
								<td><button class="btn btn-link delete">Borrar</button></td>
							</tr>
							<tr>
								<td>0003</td>
								<td>Fernandez</td>
								<td>Mauricio</td>
								<td>14/12/2001</td>
								<td>Avenida 678</td>
								<td>Ciudad Autónoma de Buenos Aires</td>
								<td>0</td>
								<td>Microcentro</td>
								<td>0</td>
								<td>mauricio@alumno.com</td>
								<td>1556781234</td>
								<td><button class="btn btn-link edit">Editar</button></td>
								<td><button class="btn btn-link delete">Borrar</button></td>
							</tr>
						</tbody>
		            </table>
		        </div>
		    </div>
		</div>
	</section>

<script src="../../Content/js/datatable.js"></script>

</t:userpage>