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

import abm.UsuarioABM;
import datos.Permiso;
import datos.Usuario;

@Controller
@RequestMapping(path="/Usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioABM usuarioABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Usuario> alta(@RequestBody Usuario[] usuarioArr) {
		List<Usuario> lstUsuariosAgregados = new ArrayList<Usuario>();
		
		for (Usuario usuario: usuarioArr) {
			try {
				usuarioABM.save(usuario);
				lstUsuariosAgregados.add(usuario);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstUsuariosAgregados;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Usuario> baja(@RequestBody Usuario[] usuarioArr) {
		List<Usuario> lstUsuariosEliminados = new ArrayList<Usuario>();
		
		for (Usuario usuario: usuarioArr) {
			try {
				usuarioABM.delete(usuario);
				lstUsuariosEliminados.add(usuario);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstUsuariosEliminados;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Usuario> modificacion(@RequestBody Usuario[] usuarioArr) {
		List<Usuario> lstUsuariosActualizados = new ArrayList<Usuario>();
		
		for (Usuario usuario: usuarioArr) {
			try {
				usuarioABM.save(usuario);
				lstUsuariosActualizados.add(usuario);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstUsuariosActualizados;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Usuario> traer(@RequestBody Usuario[] usuarioArr) {
		Iterator<Permiso> itrPermiso;
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		Usuario u;
		
		for (Usuario usuario: usuarioArr) {
			try {
				u = usuarioABM.findById(usuario.getIdPersona()).get();
				
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrPermiso = u.getLstPermiso().iterator();
				while (itrPermiso.hasNext())
				{
					Permiso permiso = itrPermiso.next();
					permiso.getLstUsuario().clear();
				}
				
				lstUsuarios.add(u);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstUsuarios;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Usuario> traerTodo() {
		Iterable<Usuario> itrUsuario = usuarioABM.findAll();
		Iterator<Permiso> itrPermiso;
		List<Usuario> lstUsuarios = new ArrayList<Usuario>();
		
		for (Usuario usuario: itrUsuario) {
			try {
				//Rutina para romper bucle infinito en muchos a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrPermiso = usuario.getLstPermiso().iterator();
				while (itrPermiso.hasNext())
				{
					Permiso permiso = itrPermiso.next();
					permiso.getLstUsuario().clear();
				}
				
				lstUsuarios.add(usuario);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstUsuarios;
	}
}