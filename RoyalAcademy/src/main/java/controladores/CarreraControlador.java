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

import abm.CarreraABM;
import datos.Carrera;

@Controller
@RequestMapping(path="/Carrera")
public class CarreraControlador {
	
	@Autowired
	private CarreraABM carreraABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Carrera> alta(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarreraAgregada = new ArrayList<Carrera>();
		
		for (Carrera carrera: carreraArr) {
			try {
				carrera.setIdCarrera(0);         //Para evitar que sobreescriba si se le manda algo con ID
				carreraABM.save(carrera);
				lstCarreraAgregada.add(carrera);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCarreraAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Carrera> baja(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarreraEliminada = new ArrayList<Carrera>();
		
		for (Carrera carrera: carreraArr) {
			try {
				carreraABM.delete(carrera);
				lstCarreraEliminada.add(carrera);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCarreraEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Carrera> modificacion(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarreraActualizada = new ArrayList<Carrera>();
		
		for (Carrera carrera: carreraArr) {
			try {
				carreraABM.save(carrera);
				lstCarreraActualizada.add(carrera);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCarreraActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Carrera> traer(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarrera = new ArrayList<Carrera>();
		Carrera c;
		
		for (Carrera carrera: carreraArr) {
			try {
				c = carreraABM.findById(carrera.getIdCarrera()).get();
				
				//Rutina para romper bucle infinito en muchos a uno, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				c.getArea().getLstCarrera().clear();
				lstCarrera.add(c);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCarrera;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Carrera> traerTodo() {
		Iterable<Carrera> itrCarrera = carreraABM.findAll();
		List<Carrera> lstCarrera = new ArrayList<Carrera>();
		
		for (Carrera carrera: itrCarrera) {
			try {
				//Rutina para romper bucle infinito en muchos a uno, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				carrera.getArea().getLstCarrera().clear();
				lstCarrera.add(carrera);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCarrera;
	}
}
