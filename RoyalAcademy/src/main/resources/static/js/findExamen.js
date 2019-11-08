(function($) {
  "use strict"; // Start of use strict

  var turno = null;
  var cursada = null;
  var simulado = null;
  var manual = null;
  

  $("select.cursada").change(function(){
      cursada = $(this).children("option:selected").val();
  });
  
  $("select.turno").change(function(){
      turno = $(this).children("option:selected").val();
  });
  
  
  $("#submit").click(function(e) {
  
      var dataToPost = {
          cursada,
          turno
      };
      
     
      console.log(JSON.stringify(dataToPost))
      
      if(turno === null || cursada === null ){
    	  alert("Tenes que seleccionar turno y cursada")
    	  return
      }
      
      window.location.href = "examenes" + "?cursada=" + cursada + "&turno=" + turno;
//    	  $.ajax(
//    		      {
//    		          type: "GET",
//    		          data: JSON.stringify(dataToPost),
//    		          url: url,
//    		          contentType: 'application/json; charset=utf-8',
//    		          success: function (result) {
//    		        	    console.log("Resultado" + result)
//    		        	   
//    		        	//    window.location.href = result;
//    		        	    } ,
//    		          error: function (err) {
//    		        	        console.log(err)
//    		        	        } 
//    		      });
     
  });

})(jQuery); // End of use strict
