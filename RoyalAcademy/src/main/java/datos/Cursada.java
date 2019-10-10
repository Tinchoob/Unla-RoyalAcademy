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
import javax.persistence.OneToMany;

@Entity
public class Cursada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCursada;
	private String codigo;
	
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "cursada")
	private Set<Examen> lstExamen;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idMateria")
	private Materia materia;
	
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
				fetch = FetchType.LAZY)
	@JoinTable(name = "ProfesorCursada",
	joinColumns = { @JoinColumn(name = "idCursada") },
	inverseJoinColumns = { @JoinColumn(name = "idPersona") })
	private Set<Profesor> lstProfesor;
	
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY)
	@JoinTable(name ="AlumnoCursada",
	joinColumns = { @JoinColumn (name= "idCursada") },
	inverseJoinColumns = { @JoinColumn(name = "idPersona") })
	private Set<Alumno> lstAlumno;

	
			
			
	
	public Cursada(int idCursada, String codigo, Materia materia) {
		super();
		this.idCursada = idCursada;
		this.codigo = codigo;
		this.materia = materia;
	}

	public Cursada() {}

	public int getIdCursada() {
		return idCursada;
	}

	public void setIdCursada(int idCursada) {
		this.idCursada = idCursada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public Set<Examen> getLstExamen() {
		return lstExamen;
	}

	public void setLstExamen(Set<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	
	public Set<Alumno> getLstAlumno() {
		return lstAlumno;
	}

	public void setLstAlumno(Set<Alumno> lstAlumno) {
		this.lstAlumno = lstAlumno;
	}

	public Set<Profesor> getLstProfesor() {
		return lstProfesor;
	}

	public void setLstProfesor(Set<Profesor> lstProfesor) {
		this.lstProfesor = lstProfesor;
	}

	
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias()
	{
		this.setMateria(null);
		this.getLstProfesor().clear();
		this.getLstAlumno().clear();
		this.getLstExamen().clear();
	
	}
		
	

	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Profesor> itrProfesor = this.getLstProfesor().iterator();
		Iterator<Alumno> itrAlumno = this.getLstAlumno().iterator();
		Iterator<Examen> itrExamen = this.getLstExamen().iterator();
		this.getMateria().limpiarReferenciasCiclicasPropias();
		while (itrProfesor.hasNext())
		{
			Profesor profesor = itrProfesor.next();
			profesor.limpiarReferenciasCiclicasPropias();
		}
		while (itrAlumno.hasNext())
		{
			Alumno alumno = itrAlumno.next();
			alumno.limpiarReferenciasCiclicasPropias();
		}
		while (itrExamen.hasNext())
		{
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Cursada [idCursada=" + idCursada + ", codigo=" + codigo + ", materia=" + materia + ", lstProfesor="
				+ lstProfesor + "]";
	}
}
