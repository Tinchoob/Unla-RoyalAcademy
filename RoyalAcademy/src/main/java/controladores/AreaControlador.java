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

import abm.AreaABM;
import datos.Area;
import datos.Carrera;

@Controller
@RequestMapping(path="/Area")
public class AreaControlador {
	
	@Autowired
	private AreaABM areaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody List<Area> alta(@RequestBody Area[] areaArr) {
		List<Area> lstAreaAgregada = new ArrayList<Area>();
		
		for (Area area: areaArr) {
			try {
				area.setIdArea(0);         //Para evitar que sobreescriba si se le manda algo con ID
				areaABM.save(area);
				lstAreaAgregada.add(area);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAreaAgregada;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<Area> baja(@RequestBody Area[] areaArr) {
		List<Area> lstAreaEliminada = new ArrayList<Area>();
		
		for (Area area: areaArr) {
			try {
				areaABM.delete(area);
				lstAreaEliminada.add(area);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAreaEliminada;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<Area> modificacion(@RequestBody Area[] areaArr) {
		List<Area> lstAreaActualizada = new ArrayList<Area>();
		
		for (Area area: areaArr) {
			try {
				areaABM.save(area);
				lstAreaActualizada.add(area);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstAreaActualizada;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<Area> traer(@RequestBody Area[] areaArr) {
		Iterator<Carrera> itrCarrera;
		List<Area> lstArea = new ArrayList<Area>();
		Area a;
		
		for (Area area: areaArr) {
			try {
				a = areaABM.findById(area.getIdArea()).get();
				
				//Rutina para romper bucle infinito en uno a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrCarrera = a.getLstCarrera().iterator();
				while (itrCarrera.hasNext())
				{
					Carrera carrera = itrCarrera.next();
					carrera.setArea(null);
				}
				
				lstArea.add(a);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstArea;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<Area> traerTodo() {
		Iterable<Area> itrArea = areaABM.findAll();
		Iterator<Carrera> itrCarrera;
		List<Area> lstArea = new ArrayList<Area>();
		
		for (Area area: itrArea) {
			try {
				//Rutina para romper bucle infinito en uno a muchos, accede a los objetos que contiene y les limpia
				//las referencias que causan ese error
				itrCarrera = area.getLstCarrera().iterator();
				while (itrCarrera.hasNext())
				{
					Carrera carrera = itrCarrera.next();
					carrera.setArea(null);
				}
				
				lstArea.add(area);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstArea;
	}
}
