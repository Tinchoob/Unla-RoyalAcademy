package datos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pregunta")
public abstract class RespuestaExamen {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRespuestaExamen;
	
	//En JoinColumns se tiene que especificar name y referencedColumnName, name es el nombre de la columna
	//referencia, o sea la foreign, y referencedColumnName el nombre de la columna original
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "idExamen", referencedColumnName = "idExamen"),
		@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	})
	private Nota nota;

	public RespuestaExamen(int idRespuestaExamen, Nota nota) {
		super();
		this.idRespuestaExamen = idRespuestaExamen;
		this.nota = nota;
	}

	public RespuestaExamen() {}

	public int getIdRespuestaExamen() {
		return idRespuestaExamen;
	}

	public void setIdRespuestaExamen(int idRespuestaExamen) {
		this.idRespuestaExamen = idRespuestaExamen;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.setNota(null);
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas() {
		this.getNota().limpiarReferenciasCiclicasPropias();
	}

	@Override
	public String toString() {
		return "RespuestaExamen [idRespuestaExamen=" + idRespuestaExamen + ", nota=" + nota + "]";
	}
}
