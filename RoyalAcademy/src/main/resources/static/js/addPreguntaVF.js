(function($) {
	"use strict"; // Start of use strict

	var materia = null;
	var selected = [];

	$("#true").click(function(e) {

		var checkbox = $(this);
		if (!checkbox.is(":checked")) {
			e.preventDefault();
			return false;
		}

		$('#false').prop('checked', false);

	});

	$("#false").click(function(e) {

		var checkbox = $(this);
		if (!checkbox.is(":checked")) {
			e.preventDefault();
			return false;
		}

		$('#true').prop('checked', false);

	});

	$("select.materia").change(function() {
		materia = $(this).children("option:selected").val();
	});

	$("#submit").click(function(e) {

		var pregunta = $('#pregunta').val();

		var trueval = $('#true:checked').val();
		var falseval = $('#false:checked').val();

		var isTrue = false;

		if (trueval) {
			isTrue = true;
		}

		var dataToPost = {
			pregunta : pregunta,
			valorCorrecto : isTrue,
			idMateria: materia
		};

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

