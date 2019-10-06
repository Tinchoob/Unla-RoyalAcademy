package datos;

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
	private Set<Materia> lstMateria;

	public Profesor(Integer idPersona, String nombre, String apellido, String numeroDocumento, String email,
			GregorianCalendar fechaNacimiento, String genero, String celular, String pais, String ciudad,
			TipoDocumento tipoDocumento) {
		super(idPersona, nombre, apellido, numeroDocumento, email, fechaNacimiento, genero, celular, pais, ciudad,
				tipoDocumento);
	}

	public Profesor() {}

	public Set<Materia> getLstMateria() {
		return lstMateria;
	}

	public void setLstMateria(Set<Materia> lstMateria) {
		this.lstMateria = lstMateria;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias()
	{
		this.getLstMateria().clear();
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
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
		return "Profesor [lstMateria=" + lstMateria + "]";
	}
}
