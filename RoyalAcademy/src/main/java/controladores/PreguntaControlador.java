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

import abm.PreguntaABM;
import datos.Pregunta;

@Controller
@RequestMapping(path="/Pregunta")
public class PreguntaControlador {
	
	@Autowired
	private PreguntaABM preguntaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Pregunta> alta(@RequestBody Pregunta[] preguntaArr) {
		List<Pregunta> lstPreguntaAgregada = new ArrayList<Pregunta>();
		
		for (Pregunta pregunta: preguntaArr) {
			try {
				pregunta.setIdPregunta(0);
				//Para evitar que sobreescriba si se le manda algo con ID
				preguntaABM.save(pregunta);
				lstPreguntaAgregada.add(pregunta);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Pregunta> baja(@RequestBody Pregunta[] preguntaArr) {
		List<Pregunta> lstPreguntaEliminada = new ArrayList<Pregunta>();
		
		for (Pregunta pregunta: preguntaArr) {
			try {
				preguntaABM.delete(pregunta);
				lstPreguntaEliminada.add(pregunta);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Pregunta> modificacion(@RequestBody Pregunta[] preguntaArr) {
		List<Pregunta> lstPreguntaActualizada = new ArrayList<Pregunta>();
		
		for (Pregunta pregunta: preguntaArr) {
			try {
				preguntaABM.save(pregunta);
				lstPreguntaActualizada.add(pregunta);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Pregunta> traer(@RequestBody Pregunta[] preguntaArr) {
		List<Pregunta> lstPregunta = new ArrayList<Pregunta>();
		Pregunta p;
		
		for (Pregunta pregunta: preguntaArr) {
			try {			
				p = preguntaABM.findById(pregunta.getIdPregunta()).get();
				p.limpiarReferenciasCiclicasExternas();
				lstPregunta.add(p);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPregunta;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Pregunta> traerTodo() {
		Iterable<Pregunta> itrPregunta = preguntaABM.findAll();
		List<Pregunta> lstPregunta = new ArrayList<Pregunta>();
		
		for (Pregunta pregunta: itrPregunta) {
			try {
				pregunta.limpiarReferenciasCiclicasExternas();
				lstPregunta.add(pregunta);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPregunta;
	}
}
