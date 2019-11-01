(function($) {
	"use strict"; // Start of use strict

	var selected = [];
	var materia = null;

	$("#opcion1").click(function(e) {
		

		var checkbox = $(this);
		if (!checkbox.is(":checked")) {
			e.preventDefault();
			return false;
		}

		$('#opcion2').prop('checked', false);
		$('#opcion3').prop('checked', false);

	});

	$("#opcion2").click(function(e) {

		var checkbox = $(this);
		if (!checkbox.is(":checked")) {
			e.preventDefault();
			return false;
		}

		$('#opcion1').prop('checked', false);
		$('#opcion3').prop('checked', false);

	});
	

	$("#opcion3").click(function(e) {

		var checkbox = $(this);
		if (!checkbox.is(":checked")) {
			e.preventDefault();
			return false;
		}

		$('#opcion2').prop('checked', false);
		$('#opcion1').prop('checked', false);

	});
	
	$("select.materia").change(function() {
		materia = $(this).children("option:selected").val();
	});


	$("#submit").click(function(e) {
		
		var pregunta = $('#pregunta').val();
		
		var respuesta1 = $('#respuesta1').val();
		var respuesta2 = $('#respuesta2').val();
		var respuesta3 = $('#respuesta3').val();
		
		var opcion1 = $('#opcion1:checked').val();
		var opcion2 = $('#opcion2:checked').val();
		var opcion3 = $('#opcion3:checked').val();
		
		var correct = 0;

		if (opcion2) {
			correct = 1;
		}
		else if(opcion3){
			correct = 2;
		}
		else if(opcion1){
			correct = 0;
		}
		else{
			alert("Debe haber seleccionado por lo menos una respuesta como correcta")
			return false;
		}

		var opciones = []
		
		opciones.push({respuesta: respuesta1})
		opciones.push({respuesta: respuesta2})
		opciones.push({respuesta: respuesta3})
		
		var dataToPost = {
			pregunta : pregunta,
			valorCorrecto : correct,
			activa: true,
			lstRespuestaMC : opciones,
			idMateria: materia
		};

		console.log(dataToPost)

		console.log(JSON.stringify(dataToPost))

		$.ajax({
			type : "POST",
			data : JSON.stringify(dataToPost),
			url : "",
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("Resultado" + result)
				// window.location.href = result;
			},
			error : function(err) {
				console.log(err)
			}
		});

		e.preventDefault();
		return false;
	
	});

})(jQuery); // End of use strict

