package controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import abm.MateriaABM;
import abm.PreguntaVFABM;
import datos.PreguntaVF;
import datos.PreguntaVFDTO;

@Controller
@RequestMapping(path = "/PreguntaVF")
public class PreguntaVFControlador {

	@Autowired
	private PreguntaVFABM preguntaVFABM;
	
	@Autowired
	private MateriaABM materiaABM;

	@GetMapping(path = "/add")
	public String alta() {

		return "addPreguntaVF";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/json" })
	public @ResponseBody ResponseEntity<String> alta(@RequestBody PreguntaVFDTO preguntaDTO) {

		PreguntaVF preguntaVF = new PreguntaVF();
		preguntaVF.setMateria(materiaABM.findById(preguntaDTO.getIdMateria()).get());
		preguntaVF.setPregunta(preguntaDTO.getPregunta());
		preguntaVF.setValorCorrecto(preguntaDTO.isValorCorrecto());
		preguntaVF.setActiva(true);
		System.out.println(preguntaDTO);
		try {
			preguntaVF.setIdPregunta(0); // Para evitar que sobreescriba si se le manda algo con ID
			preguntaVFABM.save(preguntaVF);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Hubo un error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Pregunta almacenada correctamente",HttpStatus.OK);
	}

	@PostMapping(path = "/delete")
	public @ResponseBody List<PreguntaVF> baja(@RequestBody PreguntaVF[] preguntaVFArr) {
		List<PreguntaVF> lstPreguntaVFEliminada = new ArrayList<PreguntaVF>();

		for (PreguntaVF preguntaVF : preguntaVFArr) {
			try {
				preguntaVFABM.delete(preguntaVF);
				lstPreguntaVFEliminada.add(preguntaVF);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstPreguntaVFEliminada;
	}

	@PostMapping(path = "/update")
	public @ResponseBody List<PreguntaVF> modificacion(@RequestBody PreguntaVF[] preguntaVFArr) {
		List<PreguntaVF> lstPreguntaVFActualizada = new ArrayList<PreguntaVF>();

		for (PreguntaVF preguntaVF : preguntaVFArr) {
			try {
				preguntaVFABM.save(preguntaVF);
				lstPreguntaVFActualizada.add(preguntaVF);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstPreguntaVFActualizada;
	}

	@GetMapping(path = "/read")
	public @ResponseBody List<PreguntaVF> traer(@RequestBody PreguntaVF[] preguntaVFArr) {
		List<PreguntaVF> lstPreguntaVF = new ArrayList<PreguntaVF>();
		PreguntaVF a;

		for (PreguntaVF preguntaVF : preguntaVFArr) {
			try {
				a = preguntaVFABM.findById(preguntaVF.getIdPregunta()).get();
				a.limpiarReferenciasCiclicasExternas();
				lstPreguntaVF.add(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstPreguntaVF;
	}

	@GetMapping(path = "/readAll")
	public @ResponseBody List<PreguntaVF> traerTodo() {
		Iterable<PreguntaVF> itrPreguntaVF = preguntaVFABM.findAll();
		List<PreguntaVF> lstPreguntaVF = new ArrayList<PreguntaVF>();

		for (PreguntaVF preguntaVF : itrPreguntaVF) {
			try {
				preguntaVF.limpiarReferenciasCiclicasExternas();
				lstPreguntaVF.add(preguntaVF);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstPreguntaVF;
	}
}
