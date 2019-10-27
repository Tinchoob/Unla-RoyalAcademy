(function($) {
  "use strict"; // Start of use strict

  $('input[type=radio][name=automatico]').change(function() {
	  $('input[type=radio][name=manual]').prop('checked', false); 
	});
  
  $('input[type=radio][name=manual]').change(function() {
	  $('input[type=radio][name=automatico]').prop('checked', false); 
	});


})(jQuery); // End of use strict
