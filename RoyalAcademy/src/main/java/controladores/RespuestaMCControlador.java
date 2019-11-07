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

import abm.RespuestaMCABM;
import datos.RespuestaMC;

@Controller
@RequestMapping(path="/RespuestaMC")
public class RespuestaMCControlador {
	
	@Autowired
	private RespuestaMCABM respuestaMCABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<RespuestaMC> alta(@RequestBody RespuestaMC[] respuestaMCArr) {
		List<RespuestaMC> lstRespuestaMCAgregada = new ArrayList<RespuestaMC>();
		
		for (RespuestaMC respuestaMC: respuestaMCArr) {
			try {
				respuestaMC.setIdRespuestaMC(0);         //Para evitar que sobreescriba si se le manda algo con ID
				respuestaMCABM.save(respuestaMC);
				lstRespuestaMCAgregada.add(respuestaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstRespuestaMCAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<RespuestaMC> baja(@RequestBody RespuestaMC[] respuestaMCArr) {
		List<RespuestaMC> lstRespuestaMCEliminada = new ArrayList<RespuestaMC>();
		
		for (RespuestaMC respuestaMC: respuestaMCArr) {
			try {
				respuestaMCABM.delete(respuestaMC);
				lstRespuestaMCEliminada.add(respuestaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstRespuestaMCEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<RespuestaMC> modificacion(@RequestBody RespuestaMC[] respuestaMCArr) {
		List<RespuestaMC> lstRespuestaMCActualizada = new ArrayList<RespuestaMC>();
		
		for (RespuestaMC respuestaMC: respuestaMCArr) {
			try {
				respuestaMCABM.save(respuestaMC);
				lstRespuestaMCActualizada.add(respuestaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstRespuestaMCActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<RespuestaMC> traer(@RequestBody RespuestaMC[] respuestaMCArr) {
		List<RespuestaMC> lstRespuestaMC = new ArrayList<RespuestaMC>();
		RespuestaMC a;
		
		for (RespuestaMC respuestaMC: respuestaMCArr) {
			try {
				a = respuestaMCABM.findById(respuestaMC.getIdRespuestaMC()).get();
				a.limpiarReferenciasCiclicasExternas();
				lstRespuestaMC.add(a);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstRespuestaMC;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<RespuestaMC> traerTodo() {
		Iterable<RespuestaMC> itrRespuestaMC = respuestaMCABM.findAll();
		List<RespuestaMC> lstRespuestaMC = new ArrayList<RespuestaMC>();
		
		for (RespuestaMC respuestaMC: itrRespuestaMC) {
			try {
				respuestaMC.limpiarReferenciasCiclicasExternas();
				lstRespuestaMC.add(respuestaMC);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstRespuestaMC;
	}
}
