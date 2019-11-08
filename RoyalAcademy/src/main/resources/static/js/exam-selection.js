(function($) {
  "use strict"; // Start of use strict

  var turno = null;
  var cursada = null;
  var simulado = null;
  var manual = null;
  var cantidad = null;
  
  $('input[type=radio][name=automatico]').change(function() {
	  $('input[type=radio][name=manual]').prop('checked', false); 
	  $('#cantidad').show();
	  manual = null;
	  simulado = $("input[name='automatico']:checked").val();
	});
  
  $('input[type=radio][name=manual]').change(function() {
	  $('input[type=radio][name=automatico]').prop('checked', false); 
	  simulado = null;
	  $('#cantidad').hide();
	  manual = $("input[name='manual']:checked").val();
	});

  $("select.cursada").change(function(){
      cursada = $(this).children("option:selected").val();
  });
  
  $("select.turno").change(function(){
      turno = $(this).children("option:selected").val();
  });
  
  $("select.cantidad-simulados").change(function(){
      cantidad = $(this).children("option:selected").val();
  });
  
  
  $("#submit").click(function(e) {
  
      var dataToPost = {
          cursada,
          turno,
          cantidad
      };
      var url = null;
      
      if(manual){
        url = "manual";
      }
      else{
    	 url = "simulado";
      }
      
      console.log(JSON.stringify(dataToPost))
      
      if(turno === null || cursada === null ){
    	  alert("Tenes que seleccionar turno y cursada")
    	  return
      }
      
      window.location.href = url + "?cursada=" + cursada +"&turno=" + turno + "&cantidad=" + cantidad ;
    	  $.ajax(
    		      {
    		          type: "GET",
    		          data: JSON.stringify(dataToPost),
    		          url: url,
    		          contentType: 'application/json; charset=utf-8',
    		          success: function (result) {
    		        	    console.log("Resultado" + result)
    		        	    window.location.href = result;
    		        	    } ,
    		          error: function (err) {
    		        	        console.log(err)
    		        	        } 
    		      });
     
  });

})(jQuery); // End of use strict
