   

(function($) {
  "use strict"; // Start of use strict
 
  
  var idPreguntasSeleccionadas = [];
  
  $('.preguntas-mc-container input').each(function() {
	  var id = $(this).attr("name");
	  var idPregunta =  $(this).closest('div').find(".idPregunta").val();
	  
	  if(id === "opcion1v" + idPregunta){
		  $(this).change(function(){
			  console.log()
			  $('input[type=radio][name=opcion2v' + idPregunta + ']').prop('checked', false); 
			  $('input[type=radio][name=opcion3v' + idPregunta + ']').prop('checked', false); 
			  $(this).closest('div').find(".valorcorrecto").val(0);
			  console.log(	  $(this).closest('div').find(".valorcorrecto").val());
		  });
	  }
		  if(id === "opcion2v" + idPregunta){
			  $(this).change(function(){
				  $('input[type=radio][name=opcion1v' + idPregunta + ']').prop('checked', false); 
				  $('input[type=radio][name=opcion3v' + idPregunta + ']').prop('checked', false); 
				  $(this).closest('div').find(".valorcorrecto").val(1);
				  console.log(	  $(this).closest('div').find(".valorcorrecto").val());
			  });
		  }
			  if(id === "opcion3v" + idPregunta){
				  $(this).change(function(){
					  $('input[type=radio][name=opcion2v' + idPregunta + ']').prop('checked', false); 
					  $('input[type=radio][name=opcion1v' + idPregunta + ']').prop('checked', false); 
					  $(this).closest('div').find(".valorcorrecto").val(2);
					  console.log(	  $(this).closest('div').find(".valorcorrecto").val());
				  });
	  }
			  
			 
	 });
  
  $('.preguntas-vf-container input').each(function() {
	  var id = $(this).attr("name");
	  var idPregunta =  $(this).closest('div').find(".idPregunta").val();
	  
	  if(id === "falsov" + idPregunta){
		  $(this).change(function(){
			  $('input[type=radio][name=verdaderov' + idPregunta + ']').prop('checked', false); 
			  $(this).closest('div').find(".valorcorrecto").val(false);
			  console.log(	  $(this).closest('div').find(".valorcorrecto").val());
		  });
	  }
		  if(id === "verdaderov" + idPregunta){
			  $(this).change(function(){
				  $('input[type=radio][name=falsov' + idPregunta + ']').prop('checked', false); 
				  $(this).closest('div').find(".valorcorrecto").val(true);
				  console.log($(this).closest('div').find(".valorcorrecto").val());
			  });
		  }
			
	  });
  $("#submit").click(function () {
	  
	  var preguntasvfRespuestas = []
	  var preguntasmcRespuestas = []
	  var documento = $('#documento').val();
	  var idExamen = $('#idExamen').val();
	  
	  $('.pregunta-vf-row').each(function() {
		  var id = $(this).attr("name");
		  var idPregunta =  $(this).find(".idPregunta").val();
		  var valorCorrecto =  $(this).find(".valorcorrecto").val();
		  
		  preguntasvfRespuestas.push({idPregunta: idPregunta, valorCorrecto:valorCorrecto});
				
		  });
	  
	  $('.pregunta-mc-row').each(function() {
		  var id = $(this).attr("name");
		  var idPregunta =  $(this).find(".idPregunta").val();
		  var valorCorrecto =  $(this).find(".valorcorrecto").val();
		  
		  preguntasmcRespuestas.push({idPregunta: idPregunta, valorCorrecto:valorCorrecto});
				
		  });

	  
	  var dataToPost = {
			  respuestasvf: preguntasvfRespuestas,
			  respuestasmc: preguntasmcRespuestas,
			  documento,
			  idExamen
	      };
	  
	  console.log(dataToPost)
	

	  $.ajax(
      {
          type: "POST",
          data: JSON.stringify(dataToPost),
          url: "resolve/add",
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
