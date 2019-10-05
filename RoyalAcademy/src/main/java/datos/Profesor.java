package datos;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Profesor extends Persona {
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lstProfesor")
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

	@Override
	public String toString() {
		return "Profesor [lstMateria=" + lstMateria + "]";
	}
}
