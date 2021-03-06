<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true"%>

<% 
	try {
		if (!session.getAttribute("idTipoUsuario").equals(2))
			throw new Exception();
	} catch (Exception e) {
		response.setHeader("Location", request.getContextPath() + "/Login");
		response.setStatus(302);
	}
%>

<t:genericpage title="${ title }">
    <jsp:attribute name="header">
    
    	<input type="hidden" id="tipoUsuario" value="${ tipoUsuario }" />
    	
    	<script>
    		var tipoUsuario = document.getElementById('tipoUsuario').value;
    	</script>	

		<header class="main-header">
		<!-- Logo -->
			<a href="#" class="logo">
			  <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>UTN</b></span>
			<!-- logo for regular state and mobile devices -->
			  <span class="logo-lg"><b>UTN</b> - FRGP</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
			  <!-- Sidebar toggle button-->
			<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
			  <span class="sr-only">Toggle navigation</span>
			</a>
			
			<div class="navbar-custom-menu">
			  <ul class="nav navbar-nav">
			<!-- User Account: style can be found in dropdown.less -->
			<li class="dropdown user user-menu">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			    <i class="fa fa-user"></i>
			    <span class="hidden-xs">${ userName }</span>
			  </a>
			  <ul class="dropdown-menu" style="width: auto; min-width: auto;">
			    <li class="user-footer">
			      <div class="col-md-12">
			        <a href="${ mainPath }/Login" class="btn btn-default btn-flat">Cerrar sesión</a>
			      </div>
			    </li>
			  </ul>
			</li>
			<!-- Control Sidebar Toggle Button -->
<!-- 	        <li> -->
<!--             	<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a> -->
<!-- 	        </li> -->
		   	</ul>
		    </div>
		    
		  </nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
		  <!-- sidebar: style can be found in sidebar.less -->
		  <section class="sidebar">

		    <!-- sidebar menu: : style can be found in sidebar.less -->
		    <ul class="sidebar-menu" data-widget="tree">
		      <li class="header">NAVEGACIÓN PRINCIPAL</li>
<!-- 		      <li class="active treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-dashboard"></i> <span>Dashboard</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li class="active"><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li> -->
<!-- 		          <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-files-o"></i> -->
<!-- 		          <span>Layout Options</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <span class="label label-primary pull-right">4</span> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li> -->
<!-- 		        <a href="pages/widgets.html"> -->
<!-- 		          <i class="fa fa-th"></i> <span>Widgets</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <small class="label pull-right bg-green">new</small> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		      </li> -->
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-dashboard"></i> -->
<!-- 		          <span>Administración</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="../Alumno/Alumnos.jsp"><i class="fa fa-circle-o"></i>Alumnos</a></li> -->
<!-- 		          <li><a href="../Profesor/Profesores.jsp"><i class="fa fa-circle-o"></i>Profesores</a></li> -->
<!-- 		          <li><a href="../Curso/Cursos.jsp"><i class="fa fa-circle-o"></i>Cursos</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
		      <li class="treeview">
		        <a href="#">
		          <i class="fa fa-laptop"></i>
		          <span>Docente</span>
		          <span class="pull-right-container">
		            <i class="fa fa-angle-left pull-right"></i>
		          </span>
		        </a>
		        <ul class="treeview-menu">
		          <li><a href="${ mainPath }/Profesor/Cursos"><i class="fa fa-circle-o"></i>Cursos</a></li>
<!-- 		          <li><a href="${ mainPath }/Profesor/Alumnos"><i class="fa fa-circle-o"></i>Alumnos</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/general.html"><i class="fa fa-circle-o"></i> General</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/icons.html"><i class="fa fa-circle-o"></i> Icons</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li> -->
		        </ul>
		      </li>
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-edit"></i> <span>Forms</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="pages/forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li> -->
<!-- 		          <li><a href="pages/forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li> -->
<!-- 		          <li><a href="pages/forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-table"></i> <span>Tables</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li> -->
<!-- 		        <a href="pages/calendar.html"> -->
<!-- 		          <i class="fa fa-calendar"></i> <span>Calendar</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <small class="label pull-right bg-red">3</small> -->
<!-- 		            <small class="label pull-right bg-blue">17</small> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		      </li> -->
<!-- 		      <li> -->
<!-- 		        <a href="pages/mailbox/mailbox.html"> -->
<!-- 		          <i class="fa fa-envelope"></i> <span>Mailbox</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <small class="label pull-right bg-yellow">12</small> -->
<!-- 		            <small class="label pull-right bg-green">16</small> -->
<!-- 		            <small class="label pull-right bg-red">5</small> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		      </li> -->
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-folder"></i> <span>Examples</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/profile.html"><i class="fa fa-circle-o"></i> Profile</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/login.html"><i class="fa fa-circle-o"></i> Login</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/register.html"><i class="fa fa-circle-o"></i> Register</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li> -->
<!-- 		          <li><a href="${ contentPath }/AdminLTE/pages/examples/pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li class="treeview"> -->
<!-- 		        <a href="#"> -->
<!-- 		          <i class="fa fa-share"></i> <span>Multilevel</span> -->
<!-- 		          <span class="pull-right-container"> -->
<!-- 		            <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		          </span> -->
<!-- 		        </a> -->
<!-- 		        <ul class="treeview-menu"> -->
<!-- 		          <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li> -->
<!-- 		          <li class="treeview"> -->
<!-- 		            <a href="#"><i class="fa fa-circle-o"></i> Level One -->
<!-- 		              <span class="pull-right-container"> -->
<!-- 		                <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		              </span> -->
<!-- 		            </a> -->
<!-- 		            <ul class="treeview-menu"> -->
<!-- 		              <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li> -->
<!-- 		              <li class="treeview"> -->
<!-- 		                <a href="#"><i class="fa fa-circle-o"></i> Level Two -->
<!-- 		                  <span class="pull-right-container"> -->
<!-- 		                    <i class="fa fa-angle-left pull-right"></i> -->
<!-- 		                  </span> -->
<!-- 		                </a> -->
<!-- 		                <ul class="treeview-menu"> -->
<!-- 		                  <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li> -->
<!-- 		                  <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li> -->
<!-- 		                </ul> -->
<!-- 		              </li> -->
<!-- 		            </ul> -->
<!-- 		          </li> -->
<!-- 		          <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li> -->
<!-- 		        </ul> -->
<!-- 		      </li> -->
<!-- 		      <li><a href="https://adminlte.io/docs"><i class="fa fa-book"></i> <span>Documentation</span></a></li> -->
<!-- 		      <li class="header">LABELS</li> -->
<!-- 		      <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li> -->
<!-- 		      <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li> -->
<!-- 		      <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li> -->
		    </ul>
		  </section>
		  <!-- /.sidebar -->
		</aside>

    </jsp:attribute>
    <jsp:attribute name="footer">

	<footer class="main-footer">
	    <div class="pull-right hidden-xs">
	    	Laboratorio de Computacion IV - 2C - 2019
	    </div>
	    <strong>Juan Gonzalez</strong>
  	</footer>

  <!-- Control Sidebar -->
<!--   <aside class="control-sidebar control-sidebar-dark" style="display: none;"> -->
<!--     Create the tabs -->
<!--     <ul class="nav nav-tabs nav-justified control-sidebar-tabs"> -->
<!--       <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li> -->
<!--       <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li> -->
<!--     </ul> -->
<!--     Tab panes -->
<!--     <div class="tab-content"> -->
<!--       Home tab content -->
<!--       <div class="tab-pane" id="control-sidebar-home-tab"> -->
<!--         <h3 class="control-sidebar-heading">Recent Activity</h3> -->
<!--         <ul class="control-sidebar-menu"> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <i class="menu-icon fa fa-birthday-cake bg-red"></i> -->

<!--               <div class="menu-info"> -->
<!--                 <h4 class="control-sidebar-subheading">Langdon's Birthday</h4> -->

<!--                 <p>Will be 23 on April 24th</p> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <i class="menu-icon fa fa-user bg-yellow"></i> -->

<!--               <div class="menu-info"> -->
<!--                 <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4> -->

<!--                 <p>New phone +1(800)555-1234</p> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <i class="menu-icon fa fa-envelope-o bg-light-blue"></i> -->

<!--               <div class="menu-info"> -->
<!--                 <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4> -->

<!--                 <p>nora@example.com</p> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <i class="menu-icon fa fa-file-code-o bg-green"></i> -->

<!--               <div class="menu-info"> -->
<!--                 <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4> -->

<!--                 <p>Execution time 5 seconds</p> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--         </ul> -->
<!--         /.control-sidebar-menu -->

<!--         <h3 class="control-sidebar-heading">Tasks Progress</h3> -->
<!--         <ul class="control-sidebar-menu"> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <h4 class="control-sidebar-subheading"> -->
<!--                 Custom Template Design -->
<!--                 <span class="label label-danger pull-right">70%</span> -->
<!--               </h4> -->

<!--               <div class="progress progress-xxs"> -->
<!--                 <div class="progress-bar progress-bar-danger" style="width: 70%"></div> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <h4 class="control-sidebar-subheading"> -->
<!--                 Update Resume -->
<!--                 <span class="label label-success pull-right">95%</span> -->
<!--               </h4> -->

<!--               <div class="progress progress-xxs"> -->
<!--                 <div class="progress-bar progress-bar-success" style="width: 95%"></div> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <h4 class="control-sidebar-subheading"> -->
<!--                 Laravel Integration -->
<!--                 <span class="label label-warning pull-right">50%</span> -->
<!--               </h4> -->

<!--               <div class="progress progress-xxs"> -->
<!--                 <div class="progress-bar progress-bar-warning" style="width: 50%"></div> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li> -->
<!--             <a href="javascript:void(0)"> -->
<!--               <h4 class="control-sidebar-subheading"> -->
<!--                 Back End Framework -->
<!--                 <span class="label label-primary pull-right">68%</span> -->
<!--               </h4> -->

<!--               <div class="progress progress-xxs"> -->
<!--                 <div class="progress-bar progress-bar-primary" style="width: 68%"></div> -->
<!--               </div> -->
<!--             </a> -->
<!--           </li> -->
<!--         </ul> -->
<!--         /.control-sidebar-menu -->

<!--       </div> -->
<!--       /.tab-pane -->
<!--       Stats tab content -->
<!--       <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div> -->
<!--       /.tab-pane -->
<!--       Settings tab content -->
<!--       <div class="tab-pane" id="control-sidebar-settings-tab"> -->
<!--         <form method="post"> -->
<!--           <h3 class="control-sidebar-heading">General Settings</h3> -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Report panel usage -->
<!--               <input type="checkbox" class="pull-right" checked> -->
<!--             </label> -->

<!--             <p> -->
<!--               Some information about this general settings option -->
<!--             </p> -->
<!--           </div> -->
<!--           /.form-group -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Allow mail redirect -->
<!--               <input type="checkbox" class="pull-right" checked> -->
<!--             </label> -->

<!--             <p> -->
<!--               Other sets of options are available -->
<!--             </p> -->
<!--           </div> -->
<!--           /.form-group -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Expose author name in posts -->
<!--               <input type="checkbox" class="pull-right" checked> -->
<!--             </label> -->

<!--             <p> -->
<!--               Allow the user to show his name in blog posts -->
<!--             </p> -->
<!--           </div> -->
<!--           /.form-group -->

<!--           <h3 class="control-sidebar-heading">Chat Settings</h3> -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Show me as online -->
<!--               <input type="checkbox" class="pull-right" checked> -->
<!--             </label> -->
<!--           </div> -->
<!--           /.form-group -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Turn off notifications -->
<!--               <input type="checkbox" class="pull-right"> -->
<!--             </label> -->
<!--           </div> -->
<!--           /.form-group -->

<!--           <div class="form-group"> -->
<!--             <label class="control-sidebar-subheading"> -->
<!--               Delete chat history -->
<!--               <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a> -->
<!--             </label> -->
<!--           </div> -->
<!--           /.form-group -->
<!--         </form> -->
<!--       </div> -->
<!--       /.tab-pane -->
<!--     </div> -->
<!--   </aside> -->
<!--   <!-- /.control-sidebar --> 
<!--   <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
<!--   <div class="control-sidebar-bg"></div> -->
	

    </jsp:attribute>
    <jsp:body>
      	<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
        	<jsp:doBody/>
		</div>
		<!-- /.content-wrapper -->
        
    </jsp:body>
</t:genericpage>