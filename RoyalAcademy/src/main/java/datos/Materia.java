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

@Entity
public class Materia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMateria;
	private String nombre;
	private String codigo;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY)
	@JoinTable(name = "ProfesorMateria",
    joinColumns = { @JoinColumn(name = "idMateria") },
    inverseJoinColumns = { @JoinColumn(name = "idPersona") })
	private Set<Profesor> lstProfesor;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY)
	@JoinTable(name = "MateriaCarrera",
    joinColumns = { @JoinColumn(name = "idMateria") },
    inverseJoinColumns = { @JoinColumn(name = "idCarrera") })
	private Set<Carrera> lstCarrera;

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

	public Set<Profesor> getLstProfesor() {
		return lstProfesor;
	}

	public void setLstProfesor(Set<Profesor> lstProfesor) {
		this.lstProfesor = lstProfesor;
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
		this.getLstProfesor().clear();
		this.getLstCarrera().clear();
	}
		
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Profesor> itrProfesor;
		Iterator<Carrera> itrCarrera;
		
		itrProfesor = this.getLstProfesor().iterator();
		while (itrProfesor.hasNext())
		{
			Profesor profesor = itrProfesor.next();
			profesor.limpiarReferenciasCiclicasPropias();
		}
		itrCarrera = this.getLstCarrera().iterator();
		while (itrCarrera.hasNext())
		{
			Carrera carrera = itrCarrera.next();
			carrera.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Materia [idMateria=" + idMateria + ", nombre=" + nombre + ", codigo=" + codigo + ", lstProfesor="
				+ lstProfesor + ", lstCarrera=" + lstCarrera + "]";
	}
}
