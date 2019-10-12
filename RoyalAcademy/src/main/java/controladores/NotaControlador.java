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

import abm.NotaABM;
import datos.Nota;

@Controller
@RequestMapping(path="/Nota")
public class NotaControlador {
	
	@Autowired
	private NotaABM notaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Nota> alta(@RequestBody Nota[] notaArr) {
		List<Nota> lstNotaAgregada = new ArrayList<Nota>();
		
		for (Nota nota: notaArr) {
			try {
				notaABM.save(nota);
				lstNotaAgregada.add(nota);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstNotaAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Nota> baja(@RequestBody Nota[] notaArr) {
		List<Nota> lstNotaEliminada = new ArrayList<Nota>();
		
		for (Nota nota: notaArr) {
			try {
				notaABM.delete(nota);
				lstNotaEliminada.add(nota);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstNotaEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Nota> modificacion(@RequestBody Nota[] notaArr) {
		List<Nota> lstNotaActualizada = new ArrayList<Nota>();
		
		for (Nota nota: notaArr) {
			try {
				notaABM.save(nota);
				lstNotaActualizada.add(nota);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstNotaActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Nota> traer(@RequestBody Nota[] notaArr) {
		List<Nota> lstNota = new ArrayList<Nota>();
		Nota n;
		
		for (Nota nota: notaArr) {
			try {
				n = notaABM.findById(nota.getId()).get();
				n.limpiarReferenciasCiclicasExternas();
				lstNota.add(n);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstNota;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Nota> traerTodo() {
		Iterable<Nota> itrNota = notaABM.findAll();
		List<Nota> lstNota = new ArrayList<Nota>();
		
		for (Nota nota: itrNota) {
			try {
				nota.limpiarReferenciasCiclicasExternas();
				lstNota.add(nota);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstNota;
	}
}
