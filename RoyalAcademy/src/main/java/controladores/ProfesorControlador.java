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

import abm.ProfesorABM;
import datos.Materia;
import datos.Profesor;

@Controller
@RequestMapping(path="/Profesor")
public class ProfesorControlador {
	
	@Autowired
	private ProfesorABM profesorABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Profesor> alta(@RequestBody Profesor[] profesorArr) {
		int result = 0;
		List<Profesor> lstProfesorAgregado = new ArrayList<Profesor>();
		
		for (Profesor profesor: profesorArr) {
			try {
				result = profesorABM.registerAsProfesor(profesor.getIdPersona());
				if (result == 1) lstProfesorAgregado.add(profesor);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstProfesorAgregado;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Profesor> baja(@RequestBody Profesor[] profesorArr) {
		List<Profesor> lstProfesorEliminado = new ArrayList<Profesor>();
		
		for (Profesor profesor: profesorArr) {
			try {
				profesorABM.delete(profesor);
				lstProfesorEliminado.add(profesor);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstProfesorEliminado;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Profesor> modificacion(@RequestBody Profesor[] profesorArr) {
		List<Profesor> lstProfesorActualizado = new ArrayList<Profesor>();
		
		for (Profesor profesor: profesorArr) {
			try {
				profesorABM.save(profesor);
				lstProfesorActualizado.add(profesor);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstProfesorActualizado;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Profesor> traer(@RequestBody Profesor[] profesorArr) {
		Iterator<Materia> itrMateria;
		List<Profesor> lstProfesor = new ArrayList<Profesor>();
		Profesor p;
		
		for (Profesor profesor: profesorArr) {
			try {
				p = profesorABM.findById(profesor.getIdPersona()).get();
				
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrMateria = p.getLstMateria().iterator();
				while (itrMateria.hasNext())
				{
					Materia materia = itrMateria.next();
					materia.getLstProfesor().clear();
				}
				
				lstProfesor.add(p);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstProfesor;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Profesor> traerTodo() {
		Iterable<Profesor> itrProfesor = profesorABM.findAll();
		Iterator<Materia> itrMateria;
		List<Profesor> lstProfesor = new ArrayList<Profesor>();
		
		for (Profesor profesor: itrProfesor) {
			try {
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrMateria = profesor.getLstMateria().iterator();
				while (itrMateria.hasNext())
				{
					Materia materia = itrMateria.next();
					materia.getLstProfesor().clear();
				}
				
				lstProfesor.add(profesor);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstProfesor;
	}
}
