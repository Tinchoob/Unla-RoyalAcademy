package datos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pregunta")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = PreguntaVF.class), @Type(value = PreguntaMC.class), })
public abstract class Pregunta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPregunta;
	private String pregunta;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY, mappedBy = "lstPregunta")
	private Set<Examen> lstExamen;

	public Pregunta() {}

	public Pregunta(int idPregunta, String pregunta) {
		super();
		this.idPregunta = idPregunta;
		this.pregunta = pregunta;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Set<Examen> getLstExamen() {
		return lstExamen;
	}

	public void setLstExamen(Set<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public abstract void limpiarReferenciasCiclicasPropias();
	
	//Rutina para romper bucle infinito en la serialización
	//Esta es distinta para permitir que salgan las respuestas
	public abstract void limpiarReferenciasCiclicasPropias(boolean mantenerRespuestas);

	//Rutina para romper bucle infinito en la serialización
	public abstract void limpiarReferenciasCiclicasExternas();

	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", lstExamen=" + lstExamen + "]";
	}
}
