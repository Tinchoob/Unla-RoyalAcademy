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

import abm.AlumnoABM;
import datos.Alumno;

@Controller
@RequestMapping(path="/Alumno")
public class AlumnoControlador {
	
	@Autowired
	private AlumnoABM alumnoABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Alumno> alta(@RequestBody Alumno[] alumnoArr) {
		int result = 0;
		List<Alumno> lstAlumnoAgregado = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				result = alumnoABM.registerAsAlumno(alumno.getIdPersona());
				if (result == 1) lstAlumnoAgregado.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoAgregado;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Alumno> baja(@RequestBody Alumno[] alumnoArr) {
		List<Alumno> lstAlumnoEliminado = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				alumnoABM.delete(alumno);
				lstAlumnoEliminado.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoEliminado;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Alumno> modificacion(@RequestBody Alumno[] alumnoArr) {
		List<Alumno> lstAlumnoActualizado = new ArrayList<Alumno>();
		
		for (Alumno alumno: alumnoArr) {
			try {
				alumnoABM.save(alumno);
				lstAlumnoActualizado.add(alumno);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAlumnoActualizado;
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
