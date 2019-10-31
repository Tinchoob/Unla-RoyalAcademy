package datos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

@Entity
public class Nota {
	@EmbeddedId NotaKey id;
	int nota;
 
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
    @MapsId("idExamen")
    @JoinColumn(name = "idExamen")
    Examen examen;
 
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
    @MapsId("idPersona")
    @JoinColumn(name = "idPersona")
    Alumno alumno;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "nota")
    private Set<RespuestaExamen> lstRespuestaExamen;

	public Nota(NotaKey id, int nota, Examen examen, Alumno alumno) {
		super();
		this.id = id;
		this.nota = nota;
		this.examen = examen;
		this.alumno = alumno;
	}

	public Nota() {
		this.id = new NotaKey();
	}

	public NotaKey getId() {
		return id;
	}

	public void setId(NotaKey id) {
		this.id = id;
	}
	
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
		if (examen != null) this.getId().setIdExamen(examen.getIdExamen());
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		if (alumno != null) this.getId().setIdPersona(alumno.getIdPersona());
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.setExamen(null);
		this.setAlumno(null);
	}

	//Rutina para romper bucle infinito en la serialización
	//Deja la cursada, el turno y la materia
	public void limpiarReferenciasCiclicasExternas() {
		Cursada cursada = this.getExamen().getCursada();
		Turno turno = this.getExamen().getTurno();
		Materia materia = cursada.getMateria();
		
		cursada.limpiarReferenciasCiclicasPropias();
		materia.limpiarReferenciasCiclicasPropias();
		cursada.setMateria(materia);
		
		turno.limpiarReferenciasCiclicasPropias();
		
		this.getExamen().limpiarReferenciasCiclicasPropias();
		this.getExamen().setCursada(cursada);
		this.getExamen().setTurno(turno);		
		
		this.getAlumno().limpiarReferenciasCiclicasPropias();
	}

	@Override
	public String toString() {
		return "Nota [id=" + id + ", nota=" + nota + ", examen=" + examen + ", alumno=" + alumno + "]";
	}
}
