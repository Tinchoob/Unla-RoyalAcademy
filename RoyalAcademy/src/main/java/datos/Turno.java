package datos;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Turno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTurno;
	private GregorianCalendar fechaHoraInicio;
	private GregorianCalendar fechaHoraFin;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			   fetch = FetchType.LAZY, mappedBy = "turno")
	private Set<Examen> lstExamen;
	
	
	public Turno(int idTurno, GregorianCalendar fechaHoraInicio, GregorianCalendar fechaHoraFin) {
		super();
		this.idTurno = idTurno;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
	}

	public Turno() {}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public GregorianCalendar getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(GregorianCalendar fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public GregorianCalendar getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(GregorianCalendar fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
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
		
		while (itrExamen.hasNext()) {
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin
				+ ", lstExamen=" + lstExamen + "]";
	}
}
