  

(function($) {
  "use strict"; // Start of use strict
  
  var idPreguntasSeleccionadas = [];
  
  $("#submit").click(function () {
	  
	  $('#preguntas tr').each(function() {
		    var id = $(this).find(".idPregunta").html(); 
	
		 if($(this).find('input[type="checkbox"]').is(':checked')){
			 idPreguntasSeleccionadas.push(id)
		   }
		 });
	  
	  var idCursada = $('#cursada').val();
	  var idTurno = $('#turno').val();
	  
	  var dataToPost = {
			  idCursada,
			  idTurno,
			  idPreguntasSeleccionadas
	      };
	
	  
	  console.log(idTurno)
	  $.ajax(
      {
          type: "POST",
          data: JSON.stringify(dataToPost),
          url: "add",
          contentType: 'application/json; charset=utf-8',
          success: function (result) {
        	    console.log("Resultado" + result)
        	    // window.location.href = result;
        	    } ,
          error: function (err) {
        	        console.log("Error" + err)
        	        } 
      });
  });
  

})(jQuery); // End of use strict

