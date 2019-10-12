package datos;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Examen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idExamen;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idCursada")
	Cursada cursada;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idTurno")
	Turno turno;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "examen")
	private Set<Nota> lstNota;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH },
				fetch = FetchType.LAZY)
	@JoinTable(name = "ExamenPregunta",
	joinColumns = { @JoinColumn(name = "idExamen") },
	inverseJoinColumns = { @JoinColumn(name = "idPregunta") })
	private Set<Pregunta> lstPregunta;
	
	
	public Examen(int idExamen, Cursada cursada, Turno turno) {
		super();
		this.idExamen = idExamen;
		this.cursada = cursada;
		this.turno = turno;
	}

	public Examen() {}

	public int getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}

	public Cursada getCursada() {
		return cursada;
	}

	public void setCursada(Cursada cursada) {
		this.cursada = cursada;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Set<Nota> getLstNota() {
		return lstNota;
	}

	public void setLstNota(Set<Nota> lstNota) {
		this.lstNota = lstNota;
	}

	public Set<Pregunta> getLstPregunta() {
		return lstPregunta;
	}

	public void setLstPregunta(Set<Pregunta> lstPregunta) {
		this.lstPregunta = lstPregunta;
	}

	// Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.setCursada(null);
		this.setTurno(null);
		this.getLstNota().clear();
		this.getLstPregunta().clear();
	}

	// Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas() {
		Iterator<Pregunta> itrPregunta = this.getLstPregunta().iterator();
		Iterator<Nota> itrNota = this.getLstNota().iterator();
		
		this.getCursada().limpiarReferenciasCiclicasPropias();
		this.getTurno().limpiarReferenciasCiclicasPropias();
		while (itrNota.hasNext()) {
			Nota nota = itrNota.next();
			nota.limpiarReferenciasCiclicasPropias();
		}
		while (itrPregunta.hasNext()) {
			Pregunta pregunta = itrPregunta.next();
			pregunta.limpiarReferenciasCiclicasPropias(true); //Se mantienen las respuestas en caso de que sea MC
		}
	}

	@Override
	public String toString() {
		return "Examen [idExamen=" + idExamen + ", cursada=" + cursada + ", turno=" + turno + ", lstNota=" + lstNota
				+ ", lstPregunta=" + lstPregunta + "]";
	}
}
