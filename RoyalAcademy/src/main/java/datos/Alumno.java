package datos;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Alumno extends Persona {
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY, mappedBy = "lstAlumno")
	private Set<Cursada> lstCursada;
	
	@OneToMany(mappedBy = "alumno")
    private Set<Nota> lstNota;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY)
	@JoinTable(name = "AlumnoCarrera",
	joinColumns = { @JoinColumn(name = "idPersona") },
	inverseJoinColumns = { @JoinColumn(name = "idCarrera") })
	private Set<Carrera> lstCarrera;

	public Alumno(Integer idPersona, String nombre, String apellido, String numeroDocumento, String email,
			GregorianCalendar fechaNacimiento, String genero, String celular, String pais, String ciudad,
			TipoDocumento tipoDocumento) {
		super(idPersona, nombre, apellido, numeroDocumento, email, fechaNacimiento, genero, celular, pais, ciudad,
				tipoDocumento);
	}

	public Alumno() {}
	
	public Set<Nota> getLstNota() {
		return lstNota;
	}

	public Set<Nota> Carrera() {
		return lstNota;
	}

	public void setLstNota(Set<Nota> lstNota) {
		this.lstNota = lstNota;
	}

	public Set<Cursada> getLstCursada() {
		return lstCursada;
	}

	public void setLstCursada(Set<Cursada> lstCursada) {
		this.lstCursada = lstCursada;
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
		this.getLstCursada().clear();
		this.getLstNota().clear();
		this.getLstCarrera().clear();
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Carrera> itrCarrera = this.getLstCarrera().iterator();
		Iterator<Cursada> itrCursada = this.getLstCursada().iterator();
		Iterator<Nota> itrNota = this.getLstNota().iterator();
		
		while (itrCarrera.hasNext())
		{
			Carrera carrera = itrCarrera.next();
			carrera.limpiarReferenciasCiclicasPropias();
		}
		while (itrCursada.hasNext())
		{
			Cursada cursada = itrCursada.next();
			cursada.limpiarReferenciasCiclicasPropias();
		}
		while (itrNota.hasNext())
		{
			Nota nota = itrNota.next();
		    nota.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Alumno [lstCursada=" + lstCursada + ", lstNota=" + lstNota + ", lstCarrera=" + lstCarrera + "]";
	}
}
