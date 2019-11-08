package controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import abm.CursadaABM;
import datos.Cursada;

@Controller
@RequestMapping(path="/Login")
public class LoginControlador {
	
	
	@GetMapping()
	public String alta() {
		
		return "login";
	}
	
	@GetMapping(path="/menu")
	public String menu() {
		return "menuGeneral";
	}
	

}