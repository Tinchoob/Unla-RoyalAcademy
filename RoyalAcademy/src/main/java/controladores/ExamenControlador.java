package controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import abm.ExamenABM;
import datos.Examen;

@Controller
@RequestMapping(path="Examen")
public class ExamenControlador {
	
	@Autowired
	private ExamenABM examenABM;
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public ModelAndView inicio(ModelMap map) {
		return new ModelAndView("vistaExamenes",map);
	}
	
	@RequestMapping(value="/manual", method=RequestMethod.GET)
	@ResponseBody 
	public ModelAndView manual(@RequestParam("cursada") String cursada,@RequestParam("turno") String turno,ModelMap map) {
		map.addAttribute("cursada", cursada);
		map.addAttribute("turno", turno);
		
		//TODO : Agregar filtro de preguntas por cursada y turno
		
		System.out.println(cursada + turno);
		return new ModelAndView("examenManual",map);
	}
	
	@RequestMapping(value="/simulado", method=RequestMethod.GET)
	@ResponseBody 
	public ModelAndView simulado(@RequestParam("cursada") String cursada,@RequestParam("turno") String turno,ModelMap map) {
		map.addAttribute("cursada", cursada);
		map.addAttribute("turno", turno);
		System.out.println(cursada + turno);
		return new ModelAndView("examenSimulado",map);
	}
	
	@PostMapping(path="/add")
	public @ResponseBody List<Examen> alta(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenAgregado = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				examen.setIdExamen(0);         //Para evitar que sobreescriba si se le manda algo con ID
				examenABM.save(examen);
				lstExamenAgregado.add(examen);
			}
			catch (Exception excp){
				excp.printStackTrace();
			}
		}
		
		return lstExamenAgregado;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Examen> baja(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenEliminado = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				examenABM.delete(examen);
				lstExamenEliminado.add(examen);
			}
			catch (Exception excp){
				excp.printStackTrace();
			}
		}
		
		return lstExamenEliminado;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Examen> modificacion(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenActualizado = new ArrayList<Examen>();
		
		for (Examen examen: examenArr) {
			try {
				examenABM.save(examen);
				lstExamenActualizado.add(examen);
			}
			catch (Exception excp){
				excp.printStackTrace();
			}
		}
		
		return lstExamenActualizado;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Examen> traer(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamen = new ArrayList<Examen>();
		Examen e;
		
		for (Examen examen: examenArr) {
			try {
				e = examenABM.findById(examen.getIdExamen()).get();
				e.limpiarReferenciasCiclicasExternas();
				lstExamen.add(e);
			}
			catch (Exception excp){
				excp.printStackTrace();
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
			catch (Exception excp){
				excp.printStackTrace();
			}
		}
		
		return lstExamen;
	}
}
