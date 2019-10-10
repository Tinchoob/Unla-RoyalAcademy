package datos;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Alumno extends Persona {
	
	@OneToMany(mappedBy = "Alumno")
    private Set<Nota> lstNota;
	
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY, mappedBy = "lstAlumno")
	
	private Set<Carrera> lstCarrera;
	private Set<Cursada> lstCursada;
	private Set<Examen> lstExamen;
	

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

	public Set<Examen> getLstExamen() {
		return lstExamen;
	}

	public void setLstExamen(Set<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	
	//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasPropias()
		{
			this.getLstCarrera().clear();
			this.getLstCursada().clear();
			this.getLstExamen().clear();
			this.getLstNota().clear();
		
		}
		
		
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Carrera> itrCarrera = this.getLstCarrera().iterator();
		Iterator<Cursada> itrCursada = this.getLstCursada().iterator();
		Iterator<Examen> itrExamen = this.getLstExamen().iterator();
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
		while (itrExamen.hasNext())
		{
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
		while (itrNota.hasNext())
		{
			Nota nota = itrNota.next();
		    nota.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Alumno [lstCarrera=" + lstCarrera + ", lstCursada=" + lstCursada + ", lstExamen=" + lstExamen + "]";
	}

	
}
