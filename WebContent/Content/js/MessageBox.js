/**
 * 
 */

function messageBox(){

	this.baseSwal = Swal.mixin({
		title: '<h2>Atención</h2>',
		width: '30%',
		showCloseButton: true,
		showCancelButton: false,
		cancelButtonText: 'Cancelar',
		cancelButtonColor: '#d33',
	});
	
}

messageBox.prototype.showSuccess = function (msg) {
	this.baseSwal.fire({
		icon: 'success',
		html: '<h4>'+msg+'</h4>'
	});
}

messageBox.prototype.showInfo = function(msg) {
	this.baseSwal.fire({
		icon: 'info',
		html: '<h4>'+msg+'</h4>'
	});
}

messageBox.prototype.confirm = function(msg, confirm, cancel) {
	this.baseSwal.fire({
		icon: 'question',
		html: '<h4>'+msg+'</h4>',
		showCancelButton: true,
	}).then((result) => {
		if (result.value) {
			confirm();
		} else {
			cancel();
		}
	});
}

messageBox.prototype.showError = function(msg) {
	this.baseSwal.fire({
		icon: 'error',
		title: '<h2>Ups...</h2>',
		html: '<h4>'+msg+'</h4>'
	});
}

const MessageBox = new messageBox();