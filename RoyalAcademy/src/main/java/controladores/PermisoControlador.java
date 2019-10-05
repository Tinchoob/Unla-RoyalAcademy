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

import abm.PermisoABM;
import datos.Permiso;
import datos.Usuario;

@Controller
@RequestMapping(path="/Permiso")
public class PermisoControlador {
	
	@Autowired
	private PermisoABM permisoABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Permiso> alta(@RequestBody Permiso[] permisoArr) {
		List<Permiso> lstPermisoAgregado = new ArrayList<Permiso>();
		
		for (Permiso permiso: permisoArr) {
			try {
				permisoABM.save(permiso);
				lstPermisoAgregado.add(permiso);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPermisoAgregado;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Permiso> baja(@RequestBody Permiso[] permisoArr) {
		List<Permiso> lstPermisoEliminado = new ArrayList<Permiso>();
		
		for (Permiso permiso: permisoArr) {
			try {
				permisoABM.delete(permiso);
				lstPermisoEliminado.add(permiso);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPermisoEliminado;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Permiso> modificacion(@RequestBody Permiso[] permisoArr) {
		List<Permiso> lstPermisoActualizado = new ArrayList<Permiso>();
		
		for (Permiso permiso: permisoArr) {
			try {
				permisoABM.save(permiso);
				lstPermisoActualizado.add(permiso);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPermisoActualizado;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Permiso> traer(@RequestBody Permiso[] permisoArr) {
		Iterator<Usuario> itrUsuario;
		List<Permiso> lstPermiso = new ArrayList<Permiso>();
		Permiso p;
		
		for (Permiso permiso: permisoArr) {
			try {
				p = permisoABM.findById(permiso.getIdPermiso()).get();
				
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrUsuario = p.getLstUsuario().iterator();
				while (itrUsuario.hasNext())
				{
					Usuario usuario = itrUsuario.next();
					usuario.getLstPermiso().clear();
				}
				
				lstPermiso.add(p);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPermiso;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Permiso> traerTodo() {
		Iterable<Permiso> itrPermiso = permisoABM.findAll();
		Iterator<Usuario> itrUsuario;
		List<Permiso> lstPermiso = new ArrayList<Permiso>();
		
		for (Permiso permiso: itrPermiso) {
			try {
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrUsuario = permiso.getLstUsuario().iterator();
				while (itrUsuario.hasNext())
				{
					Usuario usuario = itrUsuario.next();
					usuario.getLstPermiso().clear();
				}
				
				lstPermiso.add(permiso);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstPermiso;
	}
}
