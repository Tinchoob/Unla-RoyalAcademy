package datos;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Examen {
	@EmbeddedId
	ExamenKey id;
	int idExamen;
	

	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY,mappedBy = "examen")
    private Set<Nota> lstNota;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "lstExamen")
	private Set<Pregunta> lstPregunta;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idTurno")
	Turno turno;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.EAGER)
	@JoinColumn(name = "idCursada")
	Cursada cursada;
	
	

	public Examen() {
		super();
	}

	
	
	public Examen(ExamenKey id, int idExamen, Turno turno, Cursada cursada) {
	super();
	this.id = id;
	this.idExamen = idExamen;
	this.turno = turno;
	this.cursada = cursada;
}



	public ExamenKey getId() {
		return id;
	}



	public void setId(ExamenKey id) {
		this.id = id;
	}



	public int getIdExamen() {
		return idExamen;
	}



	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
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



	public Turno getTurno() {
		return turno;
	}



	public void setTurno(Turno turno) {
		this.turno = turno;
	}



	public Cursada getCursada() {
		return cursada;
	}



	public void setCursada(Cursada cursada) {
		this.cursada = cursada;
	}


		//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasPropias()
		{
			this.getLstNota().clear();
			this.getLstPregunta().clear();
			setTurno(null);
			setCursada(null);
		
		}
			
		//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasExternas()
		{
			Iterator<Pregunta> itrPregunta = this.getLstPregunta().iterator();
			Iterator<Nota> itrNota = this.getLstNota().iterator();
			this.getTurno().limpiarReferenciasCiclicasPropias();
			this.getCursada().limpiarReferenciasCiclicasPropias();

			while (itrPregunta.hasNext())
			{
				Pregunta pregunta = itrPregunta.next();
				pregunta.limpiarReferenciasCiclicasPropias();
			}
			while (itrNota.hasNext())
			{
				Nota nota = itrNota.next();
				nota.limpiarReferenciasCiclicasPropias();
			}
		}

		@Override
		public String toString() {
			return "Examen [id=" + id + ", idExamen=" + idExamen + ", lstNota=" + lstNota + ", lstPregunta="
					+ lstPregunta + ", turno=" + turno + ", cursada=" + cursada + "]";
		}

	
}
