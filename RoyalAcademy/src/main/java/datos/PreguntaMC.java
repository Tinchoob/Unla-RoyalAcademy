package datos;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPregunta")
public class PreguntaMC extends Pregunta {
	
	private int valorCorrecto;
	
	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY)
	@JoinTable(name = "PreguntaRespuestaMC",
	joinColumns = { @JoinColumn(name = "idPregunta") },
	inverseJoinColumns = { @JoinColumn(name = "idRespuestaMC") })
	private Set<RespuestaMC> lstRespuestaMC;
	
	
	public PreguntaMC() {}
	
	public PreguntaMC(int idPregunta, String pregunta, int valorCorrecto) {
		super(idPregunta, pregunta);
		this.valorCorrecto = valorCorrecto;
	}
	
	public int getValorCorrecto() {
		return valorCorrecto;
	}

	public void setValorCorrecto(int valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}

	public Set<RespuestaMC> getLstRespuestaMC() {
		return lstRespuestaMC;
	}

	public void setLstRespuestaMC(Set<RespuestaMC> lstRespuestaMC) {
		this.lstRespuestaMC = lstRespuestaMC;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.limpiarReferenciasCiclicasPropias(false);
	}
	
	//Rutina para romper bucle infinito en la serialización
	//Esta es distinta para permitir que salgan las respuestas
	public void limpiarReferenciasCiclicasPropias(boolean mantenerRespuestas) {
		this.setMateria(null);
		this.getLstExamen().clear();
		
		if (!mantenerRespuestas) this.getLstRespuestaMC().clear();
		else {
			Iterator<RespuestaMC> itrRespuestaMC = this.getLstRespuestaMC().iterator();
			while (itrRespuestaMC.hasNext()) {
				RespuestaMC respuestaMC = itrRespuestaMC.next();
				respuestaMC.limpiarReferenciasCiclicasPropias();
			}
		}
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas()
	{
		Iterator<Examen> itrExamen = this.getLstExamen().iterator();
		Iterator<RespuestaMC> itrRespuestaMC = this.getLstRespuestaMC().iterator();
		
		this.getMateria().limpiarReferenciasCiclicasPropias();
		while (itrExamen.hasNext())
		{
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
		while (itrRespuestaMC.hasNext())
		{
			RespuestaMC respuestaMC = itrRespuestaMC.next();
			respuestaMC.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "PreguntaMC [getIdPregunta()=" + getIdPregunta() + ", getPregunta()=" + getPregunta() + ", isActiva()="
				+ isActiva() + ", valorCorrecto=" + valorCorrecto + ", lstRespuestaMC=" + lstRespuestaMC + "]";
	}
}
