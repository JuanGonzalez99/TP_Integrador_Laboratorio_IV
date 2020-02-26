
$(function () {
	$('#datatable').dataTable({
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
            }
        },
        /*columns: [
        	{data: 'curso'},
        	{data: 'alumnosRegular'},
        	{data: 'alumnosLibres'},
        	{data: 'alumnosTotales'},
        	{data: 'porcAlumRegular'},
        	{data: 'porcAlumLibres'},
        ],
        ajax: {
            url: mainPath + "/servletReporte",
            type: 'POST',
            dataSrc: '',
            data: function ( d ) {
            	return $.extend( {}, d, {
					materiaId: 10,//$("#dtStartAlternate").val(),
					desde: 2018,//$("#dtEndAlternate").val(),
					hasta: 2020//$("#selProvincia").val()
               });
            }
        },*/
    }); // fin dataTable()
	
	$('#search').click(function () {
		triggerLoader(true);
		/*var dataTable = $('#dataTable').DataTable();
		dataTable.ajax.reload();*/
		test();
	});
	
	initPlugins();
	
	triggerLoader(false);
	
});

function initPlugins() {
	initSelect2();
	initDatepicker();
}

function initSelect2() {
	var options = {
		placeholder: '',
		allowClear: true,
		ajax: {
			url: mainPath + '/servletMateria',
			type: 'GET',
			dataType: 'json',
			delay: 250,
			processResults: function (data) {
		        return {
		            results: $.map(data, function (item) { return { id: item.id, text: item.nombre } })
		        };
		    },
			error: function (xhr, status, error) {
		        var errorLog = xhr.status + ': ' + xhr.statusText;
		        console.log('Error - ' + errorLog);
			}
		}	
	};
	
	$('#materia').select2(options);
}

function initDatepicker() {

	var optionsDP = {
	    format: "dd/mm/yyyy",
	    weekStart: 1,
//	    todayBtn: "linked",
	    clearBtn: true,
	    language: "es",
	    autoclose: true,
	    keyboardNavigation: false,
    	format: "yyyy",
        startView: 3,
        minViewMode: 2,
        maxViewMode: 4,
//	    orientation: "bottom auto"
	};
	
	$('#desde').datepicker(optionsDP);
	$('#hasta').datepicker(optionsDP);
}

function triggerLoader(state) {
	if (state) {
		$('#tableLoader').removeClass('hidden');
	} else {
		$('#tableLoader').addClass('hidden');
	}
}

function test() {
	
	var data = {
		materiaId: $("#materia").val(),
		desde: $("#desde").val(),
		hasta: $("#hasta").val()
    };
	
	debugger;

    $.ajax({
        url: mainPath + "/servletReporte",
        type: 'POST',
        /*data: function (d) {
        	return $.extend( {}, d, {
				materiaId: $("#materia").val(),
				desde: $("#desde").val(),
				hasta: $("#hasta").val()
           });
        },*/
        data: data,
        success: fillTable,
        error: function (xhr, status, error) {
            var errorMsg = xhr.status + ': ' + xhr.statusText;
            console.log('Error - ' + errorMsg);
            
        	triggerLoader(false);
        	
            MessageBox.showError('Ha ocurrido un error inesperado');
            debugger;
    	}
    });
}

function fillTable(list) {
	
	var dataTable = $('#datatable').dataTable();
	
	dataTable.fnClearTable();

	$.each(list, function () {

		data = [];
		data.push(this.curso);
		data.push(this.alumnosRegular);
		data.push(this.alumnosLibres);
		data.push(this.alumnosTotales);
		data.push(this.porcAlumRegular);
		data.push(this.porcAlumLibres);
		
		dataTable.fnAddData(data);

	});
	
	dataTable.fnDraw();

	triggerLoader(false);
}

