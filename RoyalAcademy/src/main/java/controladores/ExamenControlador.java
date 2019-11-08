package controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import abm.AlumnoABM;
import abm.CursadaABM;
import abm.ExamenABM;
import abm.NotaABM;
import abm.PersonaABM;
import abm.PreguntaABM;
import abm.PreguntaMCABM;
import abm.PreguntaVFABM;
import abm.TurnoABM;
import datos.Alumno;
import datos.Cursada;
import datos.Examen;
import datos.ExamenDTO;
import datos.ExamenResueltoDTO;
import datos.Nota;
import datos.NotaAlumno;
import datos.Pregunta;
import datos.PreguntaMC;
import datos.PreguntaVF;
import datos.RespuestaMC;
import datos.RespuestamcDTO;
import datos.RespuestavfDTO;
import datos.Turno;

@Controller
@RequestMapping(path = "Examen")
public class ExamenControlador {

	@Autowired
	private ExamenABM examenABM;

	// Horrible pero solo dios puede judzgarme
	@Autowired
	private CursadaABM cursadaABM;

	// Este es el mas horrible de todos, movelo al js que ya hiciste
	// la query recien, villero
	// Si van a hacer algo negro por lo menos preocupense porque sea rastreable en
	// el codigo pajeros,
	// ahí le puse un nombre que identifique que tipo de preguntaABM es
	@Autowired
	private PreguntaVFABM preguntaVFABM;
	//

	@Autowired
	private PreguntaMCABM preguntaMCABM;

	@Autowired
	private TurnoABM turnoABM;

	@Autowired
	private AlumnoABM alumnoABM;

	@Autowired
	private NotaABM notaABM;

	//

	@Autowired
	private PreguntaABM<Pregunta> preguntaABM;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView inicio(ModelMap map) {
		return new ModelAndView("vistaExamenes", map);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getDataToResolve(ModelMap map) {
		return new ModelAndView("ResolveExamData", map);
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView findExamenes(ModelMap map) {
		return new ModelAndView("findExamen", map);
	}
	
	@RequestMapping(value = "/examenes", method = RequestMethod.GET)
	public ModelAndView examenes(@RequestParam("cursada") int cursada, @RequestParam("turno") int turno, ModelMap map) {
		
		List<NotaAlumno> notaAlumnos = new ArrayList();
		Examen examen = examenABM.getByCursadaAndTurno(cursada, turno).get(0);
		
		List<Nota> notas = notaABM.geyByIdExamen(examen.getIdExamen());
		
		for(Nota nota : notas) {
			nota.limpiarReferenciasCiclicasExternas();
			NotaAlumno notaAlumno = new NotaAlumno();
			notaAlumno.setAlumno(nota.getAlumno());
			notaAlumno.setNota(nota.getNota());
			notaAlumnos.add(notaAlumno);
		}	
	
		map.addAttribute("notas",notaAlumnos);
		
		System.out.println(notaAlumnos.get(0).getPersona().getNumeroDocumento());
		
		return new ModelAndView("listaExamenes",map);
	}

	
	@RequestMapping(value = "/resolve", method = RequestMethod.GET)
	public ModelAndView resolver(@RequestParam("cursada") int cursada, @RequestParam("turno") int turno,
			@RequestParam("documento") String documento, ModelMap map) {
		map.addAttribute("cursada", cursada);
		map.addAttribute("turno", turno);
		map.addAttribute("documento", documento);

		Examen examen = examenABM.getByCursadaAndTurno(cursada, turno).get(0);

		List<PreguntaMC> preguntasMC = new ArrayList();
		List<PreguntaVF> preguntasVF = new ArrayList();

		for (Pregunta p : examen.getLstPregunta()) {
			if (p instanceof PreguntaMC) {
//				List<RespuestaMC> lstRespuestas = new ArrayList();
//				for(RespuestaMC r : ((PreguntaMC) p).getLstRespuestaMC()) {
//					((PreguntaMC) p).getLstRespuestaMC().
//				}
				preguntasMC.add((PreguntaMC) p);
			} else {
				preguntasVF.add((PreguntaVF) p);
			}

		}

		for (PreguntaMC n : preguntasMC) {
			n.limpiarReferenciasCiclicasExternas();
		}

		for (PreguntaVF n : preguntasVF) {
			n.limpiarReferenciasCiclicasExternas();
		}

//		
		System.out.println(preguntasMC);

		map.addAttribute("preguntasVF", preguntasVF);
		map.addAttribute("preguntasMC", preguntasMC);
		map.addAttribute("examen", examen);
		map.addAttribute("documento", documento);
		return new ModelAndView("examView", map);
	}

	@RequestMapping(value = "/resolve/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> agregarResolucion(@RequestBody ExamenResueltoDTO examenDTO) {
		// SELECT * FROM PERSONA P inner join Alumno a on p.idPersona=a.idPersona WHERE
		// P.numeroDocumento = 40812039
		try {
			int idAlumno = alumnoABM.getIdPersonaBydni(examenDTO.getDocumento());
			Alumno alumno = alumnoABM.findById(idAlumno).get();
			int respuestasCorrectas = 0;
			int respuestasTotales = 0;
			Nota nota = new Nota();
			nota.setAlumno(alumno);
			nota.setExamen(examenABM.findById(examenDTO.getIdExamen()).get());

			for (RespuestavfDTO respuestavf : examenDTO.getRespuestasvf()) {
				PreguntaVF preguntavf = preguntaVFABM.findById(respuestavf.getIdPregunta()).get();
				if (preguntavf.isValorCorrecto() == respuestavf.isValorCorrecto()) {
					respuestasCorrectas++;
				}
				respuestasTotales++;
			}

			for (RespuestamcDTO respuestamc : examenDTO.getRespuestasmc()) {
				PreguntaMC preguntamc = preguntaMCABM.findById(respuestamc.getIdPregunta()).get();
				if (preguntamc.getValorCorrecto() == respuestamc.getValorCorrecto()) {
					respuestasCorrectas++;
				}
				respuestasTotales++;
			}

			nota.setNota(respuestasCorrectas * 10 / respuestasTotales);

			notaABM.save(nota);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>("Error al procesar, intente nuevamente",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Examen almacenado correctamente",HttpStatus.OK);
	}

	@RequestMapping(value = "/manual", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView manual(@RequestParam("cursada") String cursada, @RequestParam("turno") String turno,
			ModelMap map) {
		map.addAttribute("cursada", cursada);
		map.addAttribute("turno", turno);

		// TODO : Agregar filtro de preguntas por cursada y turno

		System.out.println(cursada + turno);
		return new ModelAndView("examenManual", map);
	}

	@RequestMapping(value = "/simulado", method = RequestMethod.GET)
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

		for (i = 0; i < 2; i++) // <----------------------------- Cambiar acá para la cantidad de exámenes a
								// generar
		{
			// Tocar acá para la cantidad de preguntas a seleccionar
			Set<Pregunta> lstPregunta = preguntaABM.traerAleatorias(cursadaObj.getMateria().getIdMateria(), 50);

			examen.setIdExamen(0);
			examen.setCursada(cursadaObj);
			examen.setTurno(turnoObj);
			examen.setLstNota(null);
			examen.setLstPregunta(lstPregunta);

			lstExamen.add(examen); // <-------------------------- Los exámenes generados se almacenan en esta lista
		}

		return new ModelAndView("examenSimulado", map);
	}

	@PostMapping(path = "/add")
	public @ResponseBody List<Examen> alta(@RequestBody ExamenDTO examen) {

		try {

			System.out.println(examen);
			Cursada cursada = cursadaABM.findById(examen.getIdCursada()).get();
			Turno turno = turnoABM.findById(examen.getIdTurno()).get();

			Set<Pregunta> lstPreguntas = new HashSet();

			for (int i = 0; i < examen.getIdPreguntasSeleccionadas().length; i++) {
				lstPreguntas.add(preguntaVFABM.findAllById(examen.getIdPreguntasSeleccionadas()[i]));
			}

			Examen examenToSave = new Examen();
			examenToSave.setTurno(turno);
			examenToSave.setCursada(cursada);
			examenToSave.setLstPregunta(lstPreguntas);
			examenToSave.setIdExamen(0);
			// Para evitar que sobreescriba si se le manda algo con ID

			examenABM.save(examenToSave);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList();
	}

	@PostMapping(path = "/delete")
	public @ResponseBody List<Examen> baja(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenEliminado = new ArrayList<Examen>();

		for (Examen examen : examenArr) {
			try {
				examenABM.delete(examen);
				lstExamenEliminado.add(examen);
			} catch (Exception excp) {
				excp.printStackTrace();
			}
		}

		return lstExamenEliminado;
	}

	@PostMapping(path = "/update")
	public @ResponseBody List<Examen> modificacion(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamenActualizado = new ArrayList<Examen>();

		for (Examen examen : examenArr) {
			try {
				examenABM.save(examen);
				lstExamenActualizado.add(examen);
			} catch (Exception excp) {
				excp.printStackTrace();
			}
		}

		return lstExamenActualizado;
	}

	@GetMapping(path = "/read")
	public @ResponseBody List<Examen> traer(@RequestBody Examen[] examenArr) {
		List<Examen> lstExamen = new ArrayList<Examen>();
		Examen e;

		for (Examen examen : examenArr) {
			try {
				e = examenABM.findById(examen.getIdExamen()).get();
				e.limpiarReferenciasCiclicasExternas();
				lstExamen.add(e);
			} catch (Exception excp) {
				excp.printStackTrace();
			}
		}

		return lstExamen;
	}

	@GetMapping(path = "/readAll")
	public @ResponseBody List<Examen> traerTodo() {
		Iterable<Examen> itrExamen = examenABM.findAll();
		List<Examen> lstExamen = new ArrayList<Examen>();

		for (Examen examen : itrExamen) {
			try {
				examen.limpiarReferenciasCiclicasExternas();
				lstExamen.add(examen);
			} catch (Exception excp) {
				excp.printStackTrace();
			}
		}

		return lstExamen;
	}
}
