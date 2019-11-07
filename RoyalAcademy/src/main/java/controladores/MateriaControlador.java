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

import abm.MateriaABM;
import datos.Materia;

@Controller
@RequestMapping(path = "/Materia")
public class MateriaControlador {
	

	@Autowired
	private MateriaABM materiaABM;
	
	@GetMapping(path = "/add")
	public String alta() {

		return "addMateria";
	}
	
	@PostMapping(path = "/add")
	public @ResponseBody void alta(@RequestBody Materia materia) {
		
			try {
				materia.setIdMateria(0);         //Para evitar que sobreescriba si se le manda algo con ID
				materiaABM.save(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Materia> baja(@RequestBody Materia[] materiaArr) {
		List<Materia> lstMateriaEliminada = new ArrayList<Materia>();
		
		for (Materia materia: materiaArr) {
			try {
				materiaABM.delete(materia);
				lstMateriaEliminada.add(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateriaEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Materia> modificacion(@RequestBody Materia[] materiaArr) {
		List<Materia> lstMateriaActualizada = new ArrayList<Materia>();
		
		for (Materia materia: materiaArr) {
			try {
				materiaABM.save(materia);
				lstMateriaActualizada.add(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateriaActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Materia> traer(@RequestBody Materia[] materiaArr) {
		List<Materia> lstMateria = new ArrayList<Materia>();
		Materia m;
		
		for (Materia materia: materiaArr) {
			try {
				m = materiaABM.findById(materia.getIdMateria()).get();
				m.limpiarReferenciasCiclicasExternas();
				lstMateria.add(m);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateria;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Materia> traerTodo() {
		Iterable<Materia> itrMateria = materiaABM.findAll();
		List<Materia> lstMateria = new ArrayList<Materia>();
		
		for (Materia materia: itrMateria) {
			try {
				materia.limpiarReferenciasCiclicasExternas();
				lstMateria.add(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateria;
	}
}
