  

(function($) {
  "use strict"; // Start of use strict
  
  var selected = [];
  
  $("#submit").click(function () {
	  
	  $('#preguntas tr').each(function() {
		    var id = $(this).find(".idPregunta").html(); 
	
		   if($(this).find('input[type="checkbox"]').is(':checked')){
		    selected.push(id)
		   }
		 });
	  
	  
	  var dataToPost = {
	          cursada,
	          turno,
	          selected
	      };
	  var cursada = $('#cursada').val();
	  var turno = $('#turno').val();
	  
	  console.log(turno)
	  $.ajax(
      {
          type: "POST",
          data: JSON.stringify(dataToPost),
          url: "../add",
          contentType: 'application/json; charset=utf-8',
          success: function (result) {
        	    console.log("Resultado" + result)
//        	    window.location.href = result;
        	    } ,
          error: function (err) {
        	        console.log(err)
        	        } 
      });
  });
  

})(jQuery); // End of use strict

