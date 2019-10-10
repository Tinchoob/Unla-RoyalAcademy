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

import abm.ExamenABM;
import datos.Examen;

@Controller
@RequestMapping(path="/Examen")
public class ExamenControlador {
	
	@Autowired
	private ExamenABM examenABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Examen> alta(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenAgregada = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				        //Para evitar que sobreescriba si se le manda algo con ID
				examenABM.save(examen);
				lstExamenAgregada.add(examen);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstExamenAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Examen> baja(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenEliminada = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				examenABM.delete(examen);
				lstExamenEliminada.add(examen);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstExamenEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Examen> modificacion(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenActualizada = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				examenABM.save(examen);
				lstExamenActualizada.add(examen);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstExamenActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Examen> traer(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamen = new ArrayList<Examen>();
		Examen ex;
		
		for (Examen examen: examenArr) {
			try {
//				ex = examenABM.findById(examen.getId()).get();
//				ex.limpiarReferenciasCiclicasExternas();
//				lstExamen.add(ex);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstExamen;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Examen> traerTodo() {
		Iterable<Examen> itrExamen = examenABM.findAll();
		List<Examen> lstExamen = new ArrayList<Examen>();
		
		for (Examen examen: itrExamen) {
			try {
				examen.limpiarReferenciasCiclicasExternas();
				lstExamen.add(examen);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstExamen;
	}
}
