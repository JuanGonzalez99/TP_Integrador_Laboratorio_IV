
	var idIndexs = [];
	var actionsIndex = [];
	var columnCount = 0;
	var hasRowId = false;
	var hasActions = false;
	
	var canEdit = true;
	var canDelete = true;
//	var hasAlumnos = false;
//	var clase = '';
//	var hasProvincia = false;
	
	var btnEdit = '<button data-toggle="tooltip" data-container="body" title="Editar" class="btn btn-sm btn-flat btn-success edit"><i class="fa fa-pencil"></i></button>';
	var btnSave = '<button data-toggle="tooltip" data-container="body" title="Guardar" class="btn btn-sm btn-flat btn-primary save"><i class="fa fa-save"></i></button>';
	var btnDelete = '<button data-toggle="tooltip" data-container="body" title="Eliminar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button>';
	var btnCancel = '<button data-toggle="tooltip" data-container="body" title="Cancelar" class="btn btn-sm btn-flat btn-danger delete"><i class="fa fa-trash-o"></i></button>';

	function btnAlumnos(idCurso) {
		return '<a href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Admin/AlumnosCurso.jsp?idCurso='+ idCurso +'">Ver alumnos</a>';
	}
	
	function triggerTableLoader(status) {
		if (status) {
			$('#tableLoader').removeClass('hidden');	
		} else {
			$('#tableLoader').addClass('hidden');
		}
	}
	
	function ajaxError(xhr, status, error) {
        var errorLog = xhr.status + ': ' + xhr.statusText;
        console.log('Error - ' + errorLog);
	}
	function ajaxErrorShow(xhr, status, error) {
        var errorMsg = xhr.status + ': ' + xhr.statusText;
        console.log('Error - ' + errorMsg);
        MessageBox.showError('Ha ocurrido un error inesperado');
        debugger;
	}
	
	function clearSearch() {
		var table = $('#datatable').DataTable();
		table.search('');
		$('input[type=search]').val('');
		table.draw();
	}
	
	$(function() {
		triggerTableLoader(true);

		$('#datatable thead tr th').each(function(i) {
			var th = $(this);
			
			if (th.hasClass('row-id')) {
				hasRowId = true;
			}
		    if (th.hasClass('id')) {
		    	idIndexs.push(i);
			}
//		    if (th.hasClass('provincia')) {
//		    	hasProvincia = true;
//		    }
//		    if (!th.hasClass('special')) {
		    	columnCount++;
//		    }
//		    if (th.hasClass('alumnos')) {
//		    	hasAlumnos = true;
//		    }
		    if (th.hasClass('actions')) {
		    	actionsIndex.push(i);
		    	hasActions = true;
		    }
		});
		
//		if (hasProvincia) {
//			loadProvincias();
//		}
		
//		clase = $('#datatable').data('clase');
		canEdit = !$('#datatable').hasClass('cant-edit');
		canDelete = !$('#datatable').hasClass('cant-delete');
		
		var oTable = $('#datatable').dataTable({
			autoWidth: false,
			columnDefs: [
	            {
	                targets: idIndexs,
//	                visible: false,
	                sClass: 'hidden',
	                searchable: false
	            },
	            {
	            	targets: actionsIndex,
	            	searchable: false
	            }
            ],
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
	        }
	    }); // fin dataTable()
		var nEditing = null;

		$('#btnAdd').click( function (e) {
			e.preventDefault();
			clearSearch();

			var data = new Array(columnCount);
			data.fill('');
			if (hasRowId)
				data[0] = 0;
//			if (hasAlumnos) {
//				data.push('<a target="_blank" href="http://localhost:8180/Juan_Gonzalez_TP_Integrador1/Views/Profesor/AlumnosProfesor.jsp" class="btn btn-link alumnos">Lista de Alumnos</a>');
//			}
			

			if (hasActions)
				data.push(btnSave + ' ' + btnCancel); //Pasa a btnDelete porque se cambia en editRow()
			
			var aiNew = oTable.fnAddData( data );
			var nRow = oTable.fnGetNodes( aiNew[0] );
			
			editRow( oTable, nRow );
			nEditing = nRow;
		} ); // fin btnAdd
		
		$(document).on('click','#datatable button.delete', function (e) {
			e.preventDefault();

			MessageBox.confirm('¿Está seguro que desea eliminar este registro?',
				()=> {
					var borrar = true;
					var nRow = $(this).parents('tr')[0];
					var aData = oTable.fnGetData(nRow);

					if (hasRowId && aData[0] != 0) {
						borrar = deleteObj(aData[0]);
					}
					
					if (borrar) {						
						oTable.fnDeleteRow( nRow );
					}
					
				},
				()=> {}
			);
			
			nEditing = null;
		} ); // fin delete
		
		$(document).on('click','#datatable button.edit', function (e) {
			e.preventDefault();

			/* Get the row as a parent of the link that was clicked on */
			var nRow = $(this).parents('tr')[0];
			
			if ( nEditing !== null && nEditing != nRow ) {
				/* Currently editing - but not this row - restore the old before continuing to edit mode */
				restoreRow( oTable, nEditing );
				editRow( oTable, nRow );
				nEditing = nRow;
			}
//			else if ( nEditing == nRow && this.innerHTML != '<i class="fa fa-pencil">' ) {
//				/* Editing this row and want to save it */
//				saveRow( oTable, nEditing );
//				nEditing = null;
//			}
			else {
				/* No edit in progress - let's start one */
				editRow( oTable, nRow );
				nEditing = nRow;
			}
		}); // fin delete
		
		$(document).on('click', '#datatable button.save', function (e) {
			var nRow = $(this).parents('tr')[0];
			saveRow( oTable, nRow );
			nEditing = null;
		});
		
		$('#btnCalificate').click(function (e) {
			e.preventDefault();
			triggerTableLoader(true);

			setTimeout(function () {				
				editAll(oTable);
	
				triggerTableLoader(false);
			}, 50);

		});
		
		$('#btnSave').click(function (e) {
			e.preventDefault();
			triggerTableLoader(true);
			
			setTimeout(function () {				
				saveAll(oTable);
	
				triggerTableLoader(false);
			}, 50);
		});
		
	}); // fin $.ready()
	
	function editAll(oTable) {
		
		var trs = $('#datatable tbody tr');
		
		$.each(trs, function () {
			var nRow = $(this);
			editRow(oTable, nRow);
		});
		
		toggleButtons();

		triggerTableLoader(false);
	}
	
	function saveAll(oTable) {

		var result = true;
		var trs = $('#datatable tbody tr');
		
		$.each(trs, function () {
			var nRow = $(this);
			if (!saveRow(oTable, nRow)) {
				result = false;
				return false;
			}
		});

		if (result)
			toggleButtons();
		else
			editAll(oTable);
	}
//	function loadProvincias() {
//		
//		$.ajax({
//			url: mainPath + '/servletProvincia',
//			type: 'GET',
//			contentType: 'application/json',
//			success: function (data) {
//				$.each(data, function () {
//			    	selectProv.append($('<option>')
//						.val(this.id)
//						.text(this.nombre));
//				});
//			},
//			error: function (xhr, status, error) {
//				console.log('Error: ' + xhr.status);
//			}
//		});
//		
//	}
	
	function toggleButtons() {
		var divCalificate = $('#btnCalificate').parent();
		var divSave = $('#btnSave').parent();
		
		if (!divCalificate.hasClass('hidden')) {
			divCalificate.addClass('hidden');
			divSave.removeClass('hidden');
		} else {
			divSave.addClass('hidden');
			divCalificate.removeClass('hidden');
		}
	}
	
	function validateRange(value, min, max) {
	    if(parseInt(value) < min || isNaN(value)) 
	        return min; 
	    else if(parseInt(value) > max) 
	        return max;
	    else 
	    	return value;
	}
	
//	var alumnos = [
//		'Mario Perez'
//		, 'Lucia Gomez'
//		, 'Mauricio Fernandez'
//	];
	
	var semestres = [
		{id: 1, text: 'Primer semestre'}
		, {id: 2, text: 'Segundo semestre'}
	];
	
	var estados = [
		{id: 0, text: ''}
		, {id: 1, text: 'Regular'}
		, {id: 2, text: 'Libre'}
	];
		
//    var selectAlum = $('<select lang="es">');
//    alumnos.forEach(function(value, i) {
//    	selectAlum.append($('<option>')
//			.val(i)
//			.text(value));
//	});
//    

	var selectProv = $('<select lang="es">');
	var selectLoc = $('<select lang="es" style="width: 150px;">');
    var selectProf = $('<select lang="es" style="width: 150px;">');
    var selectMat = $('<select lang="es" style="width: 150px;">');
    var selectAlum = $('<select lang="es" style="width: 150px;">');

    var selectSem = $('<select lang="es">');
    semestres.forEach(function(elem) {
    	selectSem.append($('<option>')
			.val(elem.id)
			.text(elem.text));
    });

	
	var optionsDP = {
	    format: "dd/mm/yyyy",
	    weekStart: 1,
//	    todayBtn: "linked",
	    clearBtn: true,
	    language: "es",
	    autoclose: false,
	    keyboardNavigation: false,
//	    orientation: "bottom auto"
	};
	
	function restoreRow( oTable, nRow ) {
		var jqTds = $('>td', nRow);
		var aData = oTable.fnGetData(nRow);

		var iMore = 0;

		idIndexs.forEach(function (val) {
			aData.splice(val+iMore, 0, '');
			iMore++;
		});

		var i = 0;

		$.each(jqTds, function () {

 			var index = $(this).index();
 			var th = oTable.find('th').eq(index);

			oTable.fnUpdate( aData[i], nRow, i, false );
			i++;
		});
		
		oTable.fnDraw();
	} // fin restoreRow()
	
	function editRow( oTable, nRow ) {
		var jqTds = $('>td', nRow);
		var aData = oTable.fnGetData(nRow);

//		var iLess = 0;
//		idIndexs.forEach(function (val){
//			aData.splice(val-iLess, 1);
//			jqTds.splice(val-iLess, 1);
//			iLess++;
//		});
	    
	    $.each(jqTds, function(i, element) {
	    	var td = $(element);
	    	var th = td.closest('table').find('th').eq(td.index());
	    	
	    	if (!th.hasClass('not-editable')) {
	    		
 	    		element.innerHTML = '';
	    			    		
	    		if (th.hasClass('select')) {
	    			var select;
	    			var options = {};
	    			
	    			if (th.hasClass('provincia')) {
		    			select = selectProv;
	    			} else if (th.hasClass('localidad')) {
	    				select = selectLoc;
	    				options["minimumInputLength"] = 3;
	    				options["ajax"] = {
	    					url: mainPath + '/servletLocalidad',
	    					type: 'GET',
    						dataType: 'json',
    						processResults: function (data) {
    					        return {
    					            results: $.map(data, function (item) { return { id: item.id, text: item.nombre } })
    					        };
    					    },
    						error: ajaxError
	    				};
	    			    var option = new Option(aData[i], aData[i-1], true, true);
	    			    select.append(option).trigger('change');

	    			} else if (th.hasClass('alumno')) {
	    				select = selectAlum;
	    				options["ajax"] = {
	    					url: mainPath + '/servletAlumno',
	    					type: 'GET',
    						dataType: 'json',
    						processResults: function (data) {
    					        return {
    					            results: $.map(data, function (item) { return { id: item.id, text: item.apellido + ", " + item.nombre + " - " + item.id } })
    					        };
    					    },
    						error: ajaxError
	    				}
	    			} else if (th.hasClass('profesor')) {
	    				select = selectProf;
	    				options["ajax"] = {
	    					url: mainPath + '/servletProfesor',
	    					type: 'GET',
    						dataType: 'json',
    						processResults: function (data) {
    					        return {
    					            results: $.map(data, function (item) { return { id: item.id, text: item.apellido + ", " + item.nombre + " - " + item.id } })
    					        };
    					    },
    						error: ajaxError
	    				};
	    			    var option = new Option(aData[i], aData[i-1], true, true);
	    			    select.append(option).trigger('change');
	    			} else if (th.hasClass('materia')) {
	    				select = selectMat;
	    				options["ajax"] = {
	    					url: mainPath + '/servletMateria',
	    					type: 'GET',
    						dataType: 'json',
    						delay: 250,
    						processResults: function (data) {
    					        return {
    					            results: $.map(data, function (item) { return { id: item.id, text: item.nombre } })
    					        };
    					    },
    						error: ajaxError
	    				};
	    			    var option = new Option(aData[i], aData[i-1], true, true);
	    			    select.append(option).trigger('change');
	    			} else if (th.hasClass('semestre')) {
	    				select = selectSem.clone();
	    			} else if (th.hasClass('estado')) {
						var td = $(this);
						var selectEst = $('<select lang="es" style="width: 100px;">');
					    estados.forEach(function(elem) {
					    	selectEst.append($('<option>')
								.val(elem.id)
								.text(elem.text));
					    });
					    var idEstado = aData[i-1];
//					    selectEst.ready(function () { selectEst.select2(); });
					    selectEst.val(idEstado).change();
					    td.append(selectEst);
					    return;
					}

	    			select.ready(function(){ select.select2(options); });
	    			td.append(select);
	    			return;
	    		} //fin hasSelect

    			if (th.hasClass('date')) {
	    		    var date = $('<input type="text" class="form-control" style="width: 100px;">')
	    				.val(aData[i]);
	    		    if (th.hasClass('year')) {
	    		    	optionsDP["format"] ="yyyy";
	    		        optionsDP["startView"] = 3;
	    		        optionsDP["minViewMode"] = 2;
	    		        optionsDP["maxViewMode"] = 4;
	    		    }
	    			date.ready(function (){ date.datepicker(optionsDP); });
	    			td.append(date);
	    			return;
	    		} 
    			
    			var inner;

    			if (th.hasClass('id')) {
	    			inner = aData[i];
	    		} else if (th.hasClass('number')) {
	    			inner = '<input type="number" class="removeArrows" style="width: 100px;" value="'+aData[i]+'">';
	    		} else if (th.hasClass('nota')) {
					inner = '<input type="number" min="1" max="10" value="'+ aData[i] +'" oninput="this.value = validateRange(this.value, 0, 10)" />';
	    		} else if (th.hasClass('actions')) {
	    			inner = btnSave + ' ' + btnDelete;
	    		} else {
	    			inner = '<input type="text" style="width: 100%;" value="'+aData[i]+'">';	    			
	    		}
    			
    			element.innerHTML = inner;		
	    		
	    	} //fin !hasNotEditable
	    	
	    }); // fin .each()

	    oTable.fnDraw();
	} // fin editRow()
	
	function saveRow( oTable, nRow ) {
		var jqTds = $('>td', nRow);
 		
		var values = {};
 		var obj = {};
		
 		$.each(jqTds, function () {
 			var td = $(this);
 			var input = td.children();
 			var index = td.index();
 			var tr = td.parent();
 			var th = oTable.find('th').eq(index);
 			var value = input.val();

			if (th.hasClass('not-editable')) {
				if (th.hasClass('number'))
					value = Number(td.text());
				else
					value = td.text();
			} else if (th.hasClass('select')) {
 				value = $('option:selected', input).text();

			} else if (th.hasClass('id')) {
 				var select = tr.find('td').eq(index+1).children();
 				value = Number(select.val());
 				
 			} else if (th.hasClass('nota')) {
 				value = deparseNota(value);

 			} else if (th.hasClass('actions')) {
 				value = btnEdit;
 				if (canDelete) {
 					value += ' ' + btnDelete;
 				}
 			}

			values["_" + index] = value;
			
 			var prop = th.data('prop');
 			if (prop != null) {
 				if (th.hasClass('date') && !th.hasClass('year')) {
 					var dateParts = value.split("/");
 					
 					var dateObj = {
						day: dateParts[0],
						month: dateParts[1],
						year: dateParts[2] 
 					};
 					
 					value = dateObj;
 				}
 				obj[prop] = value;
 			}

 		}); // fin .each()
 		
 		var result = 0;

 		if (Object.keys(obj).length > 0) {
 			result = insertOrUpdate(obj, oTable);
 		}
 		
 		if (result != -1) {
 			$.each(jqTds, function () {
 	 			var td = $(this);
 	 			var index = td.index();
 	 			var tr = td.parent();
 	 			var th = oTable.find('th').eq(index);
 	 			
 	 			var value;

 	 			if (th.hasClass('row-id') && result > 0) {
 	 				value = result;
 	 			} else if (th.hasClass('detail-alumnos') && result > 0) {
 	 				value = btnAlumnos(result);
 	 			} else {
 	 				value = values["_" + index];
 	 			}
 	 			
 	 			if (th.hasClass('nota') && !th.hasClass('select')) {
 	 				value = parseNota(value);
 	 			}

 	 			oTable.fnUpdate(value, tr, index, false);
 			});
 			
 		}
 		
 		oTable.fnDraw();
 		
 		return result != -1; 		
	} // fin saveRow()
	