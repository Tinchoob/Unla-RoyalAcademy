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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pregunta")
public abstract class Pregunta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPregunta;
	private String pregunta;
	private boolean activa;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idMateria")
	private Materia materia;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY, mappedBy = "lstPregunta")
	private Set<Examen> lstExamen;

	public Pregunta() {}

	public Pregunta(int idPregunta, String pregunta) {
		super();
		this.idPregunta = idPregunta;
		this.pregunta = pregunta;
		this.activa = true;
	}
	
	

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
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
	
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
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
		return "Pregunta [idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", activa=" + activa + ", materia="
				+ materia + ", lstExamen=" + lstExamen + "]";
	}
}
