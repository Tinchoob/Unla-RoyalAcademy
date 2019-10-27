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

import abm.TurnoABM;
import datos.Turno;

@Controller
@RequestMapping(path = "/Turno")
public class TurnoControlador {

	@Autowired
	private TurnoABM turnoABM;

	@GetMapping(path = "/add")
	public String alta() {

		return "addTurno";
	}

	@PostMapping(path = "/add")
	public @ResponseBody void alta(@RequestBody Turno turno) {

		try {
			turno.setIdTurno(0); // Para evitar que sobreescriba si se le manda algo con ID
			turnoABM.save(turno);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PostMapping(path = "/delete")
	public @ResponseBody List<Turno> baja(@RequestBody Turno[] turnoArr) {
		List<Turno> lstTurnoEliminado = new ArrayList<Turno>();

		for (Turno turno : turnoArr) {
			try {
				turnoABM.delete(turno);
				lstTurnoEliminado.add(turno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstTurnoEliminado;
	}

	@PostMapping(path = "/update")
	public @ResponseBody List<Turno> modificacion(@RequestBody Turno[] turnoArr) {
		List<Turno> lstTurnoActualizado = new ArrayList<Turno>();

		for (Turno turno : turnoArr) {
			try {
				turnoABM.save(turno);
				lstTurnoActualizado.add(turno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstTurnoActualizado;
	}

	@GetMapping(path = "/read")
	public @ResponseBody List<Turno> traer(@RequestBody Turno[] turnoArr) {
		List<Turno> lstTurno = new ArrayList<Turno>();
		Turno t;

		for (Turno turno : turnoArr) {
			try {
				t = turnoABM.findById(turno.getIdTurno()).get();
				t.limpiarReferenciasCiclicasExternas();
				lstTurno.add(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstTurno;
	}

	@GetMapping(path = "/readAll")
	public @ResponseBody List<Turno> traerTodo() {
		Iterable<Turno> itrTurno = turnoABM.findAll();
		List<Turno> lstTurno = new ArrayList<Turno>();

		for (Turno turno : itrTurno) {
			try {
				turno.limpiarReferenciasCiclicasExternas();
				lstTurno.add(turno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lstTurno;
	}
}
