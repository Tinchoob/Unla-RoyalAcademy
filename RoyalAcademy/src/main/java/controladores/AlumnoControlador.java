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

import abm.AlumnoABM;
import datos.Alumno;
import datos.Carrera;

@Controller
@RequestMapping(path="/Alumno")
public class AlumnoControlador {
	
	@Autowired
	private AlumnoABM alumnoABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Alumno> alta(@RequestBody Alumno[] alumnoArr) {
		int result = 0;
		List<Alumno> lstAlumnoAgregada = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				result = alumnoABM.registerAsAlumno(alumno.getIdPersona());
				if (result == 1) lstAlumnoAgregada.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Alumno> baja(@RequestBody Alumno[] alumnoArr) {
		List<Alumno> lstAlumnoEliminada = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				alumnoABM.delete(alumno);
				lstAlumnoEliminada.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Alumno> modificacion(@RequestBody Alumno[] alumnoArr) {
		List<Alumno> lstAlumnoActualizada = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				alumnoABM.save(alumno);
				lstAlumnoActualizada.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Alumno> traer(@RequestBody Alumno[] alumnoArr) {
		List<Alumno> lstAlumno = new ArrayList<Alumno>();
		Alumno a;
		
		for (Alumno alumno: alumnoArr) {
			try {
				a = alumnoABM.findById(alumno.getIdPersona()).get();
				a.limpiarReferenciasCiclicasExternas();
				lstAlumno.add(a);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumno;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Alumno> traerTodo() {
		Iterable<Alumno> itrAlumno = alumnoABM.findAll();
		List<Alumno> lstAlumno = new ArrayList<Alumno>();
		
		for (Alumno alumno: itrAlumno) {
			try {
				alumno.limpiarReferenciasCiclicasExternas();
				lstAlumno.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumno;
	}
}
