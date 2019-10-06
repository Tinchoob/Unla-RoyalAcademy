package datos;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Area {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idArea;
	private String nombre;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "area")
	private Set<Carrera> lstCarrera;
	
	public Area(int idArea, String nombre) {
		super();
		this.idArea = idArea;
		this.nombre = nombre;
	}
	
	public Area() {}
	
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<Carrera> getLstCarrera() {
		return lstCarrera;
	}

	public void setLstCarrera(Set<Carrera> lstCarrera) {
		this.lstCarrera = lstCarrera;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias()
	{
		this.getLstCarrera().clear();
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Carrera> itrCarrera;
		
		itrCarrera = this.getLstCarrera().iterator();
		while (itrCarrera.hasNext())
		{
			Carrera carrera = itrCarrera.next();
			carrera.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Area [idArea=" + idArea + ", nombre=" + nombre + ", lstCarrera=" + lstCarrera + "]";
	}
}
