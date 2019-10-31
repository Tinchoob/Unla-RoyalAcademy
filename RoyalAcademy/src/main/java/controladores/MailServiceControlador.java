package controladores;



import datos.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path = "/MailService")
public class MailServiceControlador {
	
	 @Autowired
	  private MailService mailService;

	    @GetMapping("/send")
	    public String sendMail(){
	        return "sendMail";
	    }

	    @PostMapping("/send")
	    public  String sendMail(@RequestParam("correo") String correo, @RequestParam("asunto") String asunto, @RequestParam("mensaje") String mensaje){

	        String message = mensaje +"\n\n Datos de contacto: " + "\nE-mail: " + correo;
	        mailService.sendMail(correo,asunto,mensaje);

	        return "sendMail";
	    }
	    
}
