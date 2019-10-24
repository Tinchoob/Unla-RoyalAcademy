package datos;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Profesor extends Persona {
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
						   fetch = FetchType.LAZY, mappedBy = "lstProfesor")
	private Set<Cursada> lstCursada;

	public Profesor(Integer idPersona, String nombre, String apellido, String numeroDocumento, String email,
			LocalDate fechaNacimiento, String genero, String celular, String pais, String ciudad,
			TipoDocumento tipoDocumento) {
		super(idPersona, nombre, apellido, numeroDocumento, email, fechaNacimiento, genero, celular, pais, ciudad,
				tipoDocumento);
	}

	public Profesor() {}
	
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
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Cursada> itrCursada = this.getLstCursada().iterator();
		
		while (itrCursada.hasNext())
		{
			Cursada cursada = itrCursada.next();
			cursada.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Profesor [lstCursada=" + lstCursada + "]";
	}
}
