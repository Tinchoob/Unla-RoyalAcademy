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

import abm.CursadaABM;
import datos.Cursada;

@Controller
@RequestMapping(path="/Cursada")
public class CursadaControlador {
	
	@Autowired
	private CursadaABM cursadaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Cursada> alta(@RequestBody Cursada[] cursadaArr) {
		List<Cursada> lstCursadaAgregada = new ArrayList<Cursada>();
		
		for (Cursada cursada: cursadaArr) {
			try {
				cursada.setIdCursada(0);         //Para evitar que sobreescriba si se le manda algo con ID
				cursadaABM.save(cursada);
				lstCursadaAgregada.add(cursada);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCursadaAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Cursada> baja(@RequestBody Cursada[] cursadaArr) {
		List<Cursada> lstCursadaEliminada = new ArrayList<Cursada>();
		
		for (Cursada cursada: cursadaArr) {
			try {
				cursadaABM.delete(cursada);
				lstCursadaEliminada.add(cursada);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCursadaEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Cursada> modificacion(@RequestBody Cursada[] cursadaArr) {
		List<Cursada> lstCursadaActualizada = new ArrayList<Cursada>();
		
		for (Cursada cursada: cursadaArr) {
			try {
				cursadaABM.save(cursada);
				lstCursadaActualizada.add(cursada);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCursadaActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Cursada> traer(@RequestBody Cursada[] cursadaArr) {
		List<Cursada> lstCursada = new ArrayList<Cursada>();
		Cursada c;
		
		for (Cursada cursada: cursadaArr) {
			try {
				c = cursadaABM.findById(cursada.getIdCursada()).get();
				c.limpiarReferenciasCiclicasExternas();
				lstCursada.add(c);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCursada;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Cursada> traerTodo() {
		Iterable<Cursada> itrCursada = cursadaABM.findAll();
		List<Cursada> lstCursada = new ArrayList<Cursada>();
		
		for (Cursada cursada: itrCursada) {
			try {
				cursada.limpiarReferenciasCiclicasExternas();
				lstCursada.add(cursada);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstCursada;
	}
}
