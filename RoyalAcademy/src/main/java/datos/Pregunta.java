package datos;

import java.util.Iterator;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pregunta")
public class Pregunta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPregunta;
	private String pregunta;




	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY)
	@JoinTable(name = "ExamenPregunta",
	joinColumns = { @JoinColumn(name = "idPregunta") },
	inverseJoinColumns = { @JoinColumn(name = "idExamen") })
	private Set<Examen> lstExamen;






	public Pregunta() {
		super();
	}

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
	public void limpiarReferenciasCiclicasPropias()
	{
		this.getLstExamen().clear();
	}

	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Examen> itrExamen = this.getLstExamen().iterator();

		while (itrExamen.hasNext())
		{
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", lstExamen=" + lstExamen + "]";
	}



}
