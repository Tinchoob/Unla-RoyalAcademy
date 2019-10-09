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
import javax.persistence.OneToMany;

@Entity
public class Materia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMateria;
	private String nombre;
	private String codigo;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY)
	@JoinTable(name = "MateriaCarrera",
    joinColumns = { @JoinColumn(name = "idMateria") },
    inverseJoinColumns = { @JoinColumn(name = "idCarrera") })
	private Set<Carrera> lstCarrera;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "materia")
	private Set<Cursada> lstCursada;

	public Materia(int idMateria, String nombre, String codigo) {
		super();
		this.idMateria = idMateria;
		this.nombre = nombre;
		this.codigo = codigo;
	}

	public Materia() {}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Set<Carrera> getLstCarrera() {
		return lstCarrera;
	}

	public void setLstCarrera(Set<Carrera> lstCarrera) {
		this.lstCarrera = lstCarrera;
	}
	
	public Set<Cursada> getLstCursada() {
		return lstCursada;
	}

	public void setLstCursada(Set<Cursada> lstCursada) {
		this.lstCursada = lstCursada;
	}

	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias()
	{
		this.getLstCursada().clear();
		this.getLstCarrera().clear();
	}
		
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Cursada> itrCursada = this.getLstCursada().iterator();
		Iterator<Carrera> itrCarrera = this.getLstCarrera().iterator();
		
		while (itrCursada.hasNext())
		{
			Cursada cursada = itrCursada.next();
			cursada.limpiarReferenciasCiclicasPropias();
		}
		while (itrCarrera.hasNext())
		{
			Carrera carrera = itrCarrera.next();
			carrera.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Materia [idMateria=" + idMateria + ", nombre=" + nombre + ", codigo=" + codigo + ", lstCarrera="
				+ lstCarrera + ", lstCursada=" + lstCursada + "]";
	}
}
