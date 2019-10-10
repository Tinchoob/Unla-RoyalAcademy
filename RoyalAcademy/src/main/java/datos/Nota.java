package datos;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

@Entity

public class Nota {
	@EmbeddedId
    NotaKey id;
 
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY)
    @MapsId("idExamen")
    @JoinColumn(name = "idExamen")
    Examen examen;
 
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY)
    @MapsId("idPersona")
    @JoinColumn(name = "idPersona")
    Alumno alumno;
 
    int nota;

    
    
    public Nota(NotaKey id, Examen examen, Alumno alumno, int nota) {
		this.id = id;
		this.examen = examen;
		this.alumno = alumno;
		this.nota = nota;
	}

	public Nota() { }

	public NotaKey getId() {
		return id;
	}

	public void setId(NotaKey id) {
		this.id = id;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	//Rutina para romper bucle infinito en la serialización
			public void limpiarReferenciasCiclicasPropias()
			{
				this.setExamen(null);
				this.setAlumno(null);
			}
			
			
		//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasExternas()
		{
			//this.getExamen().limpiarReferenciasCiclicasPropias();
			this.getAlumno().limpiarReferenciasCiclicasPropias();
		}
 
	@Override
	public String toString() {
		return "Nota [id=" + id + ", examen=" + examen + ", alumno=" + alumno + ", nota=" + nota + "]";
	}

    
}
