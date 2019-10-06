package datos;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCarrera;
	private String codigo;
	private String nombre;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
    @JoinColumn(name = "idArea")
	private Area area;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
				fetch = FetchType.LAZY, mappedBy = "lstCarrera")
	private Set<Materia> lstMateria;

	public Carrera(int idCarrera, String codigo, String nombre, Area area) {
		super();
		this.idCarrera = idCarrera;
		this.codigo = codigo;
		this.nombre = nombre;
		this.area = area;
	}

	public Carrera() {}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Set<Materia> getLstMateria() {
		return lstMateria;
	}

	public void setLstMateria(Set<Materia> lstMateria) {
		this.lstMateria = lstMateria;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias()
	{
		this.setArea(null);
		this.getLstMateria().clear();
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		this.getArea().limpiarReferenciasCiclicasPropias();
		
		Iterator<Materia> itrMateria;
		itrMateria = this.getLstMateria().iterator();
		while (itrMateria.hasNext())
		{
			Materia materia = itrMateria.next();
			materia.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", codigo=" + codigo + ", nombre=" + nombre + ", area=" + area
				+ ", lstMateria=" + lstMateria + "]";
	}
}
