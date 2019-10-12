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

import abm.PreguntaMCABM;
import datos.PreguntaMC;


@Controller
@RequestMapping(path="/PreguntaMC")
public class PreguntaMCControlador {
	
	@Autowired
	private PreguntaMCABM preguntaMCABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<PreguntaMC> alta(@RequestBody PreguntaMC[] preguntaMCArr) {
		List<PreguntaMC> lstPreguntaMCAgregada = new ArrayList<PreguntaMC>();
		
		for (PreguntaMC preguntaMC: preguntaMCArr) {
			try {
				preguntaMC.setIdPregunta(0);           //Para evitar que sobreescriba si se le manda algo con ID
				preguntaMCABM.save(preguntaMC);
				lstPreguntaMCAgregada.add(preguntaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaMCAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<PreguntaMC> baja(@RequestBody PreguntaMC[] preguntaMCArr) {
		List<PreguntaMC> lstPreguntaMCEliminada = new ArrayList<PreguntaMC>();
		
		for (PreguntaMC preguntaMC: preguntaMCArr) {
			try {
				preguntaMCABM.delete(preguntaMC);
				lstPreguntaMCEliminada.add(preguntaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaMCEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<PreguntaMC> modificacion(@RequestBody PreguntaMC[] preguntaMCArr) {
		List<PreguntaMC> lstPreguntaMCActualizada = new ArrayList<PreguntaMC>();
		
		for (PreguntaMC preguntaMC: preguntaMCArr) {
			try {
				preguntaMCABM.save(preguntaMC);
				lstPreguntaMCActualizada.add(preguntaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaMCActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<PreguntaMC> traer(@RequestBody PreguntaMC[] preguntaMCArr) {
		List<PreguntaMC> lstPreguntaMC = new ArrayList<PreguntaMC>();
		PreguntaMC a;
		
		for (PreguntaMC preguntaMC: preguntaMCArr) {
			try {
				a = preguntaMCABM.findById(preguntaMC.getIdPregunta()).get();
				a.limpiarReferenciasCiclicasExternas();
				lstPreguntaMC.add(a);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaMC;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<PreguntaMC> traerTodo() {
		Iterable<PreguntaMC> itrPreguntaMC = preguntaMCABM.findAll();
		List<PreguntaMC> lstPreguntaMC = new ArrayList<PreguntaMC>();
		
		for (PreguntaMC preguntaMC: itrPreguntaMC) {
			try {
				preguntaMC.limpiarReferenciasCiclicasExternas();
				lstPreguntaMC.add(preguntaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPreguntaMC;
	}
}
