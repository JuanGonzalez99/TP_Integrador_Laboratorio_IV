
$(function () {
	var datatable = $('#datatable').dataTable({
		autoWidth: false,
        scrollX: true,
        scrollCollapse: false,
        language: {
            sProcessing: "Procesando...",
            sLengthMenu: "Mostrar _MENU_ registros",
            sZeroRecords: "No se encontraron resultados",
            sEmptyTable: "Ningún dato disponible en esta tabla",
            sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros",
            sInfoFiltered: "(filtrado de un total de _MAX_ registros)",
            sInfoPostFix: "",
            sSearch: "Buscar:",
            sUrl: "",
            sInfoThousands: ",",
            sLoadingRecords: "Cargando...",
            oPaginate: {
                sFirst: "Primero",
                sLast: "Último",
                sNext: "Siguiente",
                sPrevious: "Anterior"
            },
            oAria: {
                sSortAscending: ": Activar para ordenar la columna de manera ascendente",
                sSortDescending: ": Activar para ordenar la columna de manera descendente"
            },
            columns: [
            	{data: 'curso'},
            	{data: 'alumnosRegular'},
            	{data: 'alumnosLibres'},
            	{data: 'alumnosTotales'},
            	{data: 'porcAlumRegular'},
            	{data: 'porcAlumLibres'},
            ],
            ajax: {
                url: mainPath + "servletReporte",
                type: 'POST',
                data: function ( d ) {
                	return $.extend( {}, d, {
						materiaId: 10,//$("#dtStartAlternate").val(),
						desde: 2018,//$("#dtEndAlternate").val(),
						hasta: 2020//$("#selProvincia").val()
                   });
                }                
            },
        }
    }); // fin dataTable()
	
	$('#search').click(function () {
		datatable.ajax.reload();
	});
	
	triggerTableLoader(false);
	
});

