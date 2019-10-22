package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import abm.CarreraABM;
import datos.Carrera;
import datos.Materia;

@Controller
@RequestMapping(path = "/Carrera")
public class CarreraControlador {

	@Autowired
	private CarreraABM carreraABM;

	@GetMapping(path = "/add")
	public String alta() {

		return "addCarrera";
	}

	@PostMapping(path = "/add" ,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody void alta(Carrera carreraArr) {

		System.out.println(carreraArr);
		try {
			carreraArr.setIdCarrera(0); // Para evitar que sobreescriba si se le manda algo con ID
			carreraABM.save(carreraArr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PostMapping(path = "/delete")
	public @ResponseBody List<Carrera> baja(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarreraEliminada = new ArrayList<Carrera>();

		for (Carrera carrera : carreraArr) {
			try {
				carreraABM.delete(carrera);
				lstCarreraEliminada.add(carrera);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstCarreraEliminada;
	}

	@PostMapping(path = "/update")
	public @ResponseBody List<Carrera> modificacion(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarreraActualizada = new ArrayList<Carrera>();

		for (Carrera carrera : carreraArr) {
			try {
				carreraABM.save(carrera);
				lstCarreraActualizada.add(carrera);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstCarreraActualizada;
	}

	@GetMapping(path = "/read")
	public @ResponseBody List<Carrera> traer(@RequestBody Carrera[] carreraArr) {
		List<Carrera> lstCarrera = new ArrayList<Carrera>();
		Carrera c;

		for (Carrera carrera : carreraArr) {
			try {
				c = carreraABM.findById(carrera.getIdCarrera()).get();
				c.limpiarReferenciasCiclicasExternas();
				lstCarrera.add(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstCarrera;
	}

	@GetMapping(path = "/readAll")
	public @ResponseBody List<Carrera> traerTodo() {
		Iterable<Carrera> itrCarrera = carreraABM.findAll();
		List<Carrera> lstCarrera = new ArrayList<Carrera>();

		for (Carrera carrera : itrCarrera) {
			try {
				carrera.limpiarReferenciasCiclicasExternas();
				lstCarrera.add(carrera);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstCarrera;
	}
}
