  

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
//	  var id = $(this)
//      .closest('.table-row') 
//      .find('.idPregunta').html();
//
//	  var checked = $('.check:checked').val()
//	  
//	  console.log(checked)
//	  if(!checked){
//		  selected.push(id)
//	  }
//	  else{
//		  var index = selected.indexOf(id);
//		  if (index > -1) {
//		    selected.splice(index, 1);
//		  }
//	  }
//	 
//	  console.log(selected)

  });
  


})(jQuery); // End of use strict

