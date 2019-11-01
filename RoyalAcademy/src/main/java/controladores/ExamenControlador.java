package controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import abm.CursadaABM;
import abm.ExamenABM;
import abm.PreguntaABM;
import abm.PreguntaVFABM;
import abm.TurnoABM;
import datos.Cursada;
import datos.Examen;
import datos.ExamenDTO;
import datos.Pregunta;
import datos.Turno;

@Controller
@RequestMapping(path="Examen")
public class ExamenControlador {
	
	@Autowired
	private ExamenABM examenABM;
	
	//Horrible pero solo dios puede judzgarme
	@Autowired
	private CursadaABM cursadaABM;
	
	//Este es el mas horrible de todos, movelo al js que ya hiciste
	//la query recien, villero
	//Si van a hacer algo negro por lo menos preocupense porque sea rastreable en el codigo pajeros,
	//ahí le puse un nombre que identifique que tipo de preguntaABM es 
	@Autowired
	private PreguntaVFABM preguntaVFABM;
	//
	
	@Autowired
	private TurnoABM turnoABM;
	
	//
	
	@Autowired
	private PreguntaABM<Pregunta> preguntaABM;
	
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
	public ModelAndView simulado(@RequestParam("cursada") int cursada, @RequestParam("turno") int turno, ModelMap map) {
		int i;
		Examen examen = new Examen();
		Cursada cursadaObj = cursadaABM.findById(cursada).get();
		Turno turnoObj = turnoABM.findById(turno).get();
		List<Examen> lstExamen = new ArrayList<Examen>();
		
		map.addAttribute("cursada", cursada);
		map.addAttribute("turno", turno);
		System.out.println(cursada + turno);
		
		for (i = 0; i < 2; i++) //<----------------------------- Cambiar acá para la cantidad de exámenes a generar 
		{
			//Tocar acá para la cantidad de preguntas a seleccionar
			Set<Pregunta> lstPregunta = preguntaABM.traerAleatorias(cursadaObj.getMateria().getIdMateria(), 50);
			
			examen.setIdExamen(0);
			examen.setCursada(cursadaObj);
			examen.setTurno(turnoObj);
			examen.setLstNota(null);
			examen.setLstPregunta(lstPregunta);
			
			lstExamen.add(examen); //<-------------------------- Los exámenes generados se almacenan en esta lista
		}
		
		return new ModelAndView("examenSimulado", map);
	}
	
	@PostMapping(path="/add")
	public @ResponseBody List<Examen> alta(@RequestBody ExamenDTO examen) {
		
		try {
	
		System.out.println(examen);
		Cursada cursada = cursadaABM.findById(examen.getIdCursada()).get();
		Turno turno = turnoABM.findById(examen.getIdTurno()).get();
		
		Set<Pregunta> lstPreguntas = new HashSet();
		
		for(int i=0 ; i < examen.getIdPreguntasSeleccionadas().length ; i++) {
				lstPreguntas.add(preguntaVFABM.findAllById(examen.getIdPreguntasSeleccionadas()[i]));		
		}
		
		Examen examenToSave = new Examen();
		examenToSave.setTurno(turno);
		examenToSave.setCursada(cursada);
		examenToSave.setLstPregunta(lstPreguntas);
		examenToSave.setIdExamen(0); 
		//Para evitar que sobreescriba si se le manda algo con ID
		
		examenABM.save(examenToSave);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList();
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
