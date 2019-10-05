package controladores;

import java.util.ArrayList;
import java.util.Iterator;
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
import datos.Profesor;

@Controller
@RequestMapping(path="/Materia")
public class MateriaControlador {
	
	@Autowired
	private MateriaABM materiaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Materia> alta(@RequestBody Materia[] materiaArr) {
		List<Materia> lstMateriaAgregada = new ArrayList<Materia>();
		
		for (Materia materia: materiaArr) {
			try {
				materiaABM.save(materia);
				lstMateriaAgregada.add(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateriaAgregada;
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
		Iterator<Profesor> itrProfesor;
		List<Materia> lstMateria = new ArrayList<Materia>();
		Materia m;
		
		for (Materia materia: materiaArr) {
			try {
				m = materiaABM.findById(materia.getIdMateria()).get();
				
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrProfesor = m.getLstProfesor().iterator();
				while (itrProfesor.hasNext())
				{
					Profesor profesor = itrProfesor.next();
					profesor.getLstMateria().clear();
				}
				
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
		Iterator<Profesor> itrProfesor;
		List<Materia> lstMateria = new ArrayList<Materia>();
		
		for (Materia materia: itrMateria) {
			try {
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrProfesor = materia.getLstProfesor().iterator();
				while (itrProfesor.hasNext())
				{
					Profesor profesor = itrProfesor.next();
					profesor.getLstMateria().clear();
				}
				
				lstMateria.add(materia);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstMateria;
	}
}
