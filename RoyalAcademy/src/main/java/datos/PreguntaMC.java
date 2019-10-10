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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPregunta")

public class PreguntaMC extends Pregunta{

	private int valorCorrecto;
	
	

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
			fetch = FetchType.LAZY)
	@JoinTable(name = "PreguntaRespuestaMC",
	joinColumns = { @JoinColumn(name = "idPreguntaMC") },
	inverseJoinColumns = { @JoinColumn(name = "idRespuesta") })
	private Set<RespuestaMC> lstRespuestaMC;
	
	public PreguntaMC() {
		super();
	}



	public PreguntaMC(int idPregunta, String pregunta) {
		super(idPregunta, pregunta);
		
	}


	public Set<RespuestaMC> getLstRespuestaMC() {
		return lstRespuestaMC;
	}



	public void setLstRespuestaMC(Set<RespuestaMC> lstRespuestaMC) {
		this.lstRespuestaMC = lstRespuestaMC;
	}



	public int getValorCorrecto() {
		return valorCorrecto;
	}



	public int isValorCorrecto() {
		return valorCorrecto;
	}

	public void setValorCorrecto(int valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}

	//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasPropias()
		{

			this.getLstRespuestaMC().clear();
		
		}
			
		

		//Rutina para romper bucle infinito en la serialización
		public void limpiarReferenciasCiclicasExternas()
		{
			Iterator<RespuestaMC> itrRespuestaMC = this.getLstRespuestaMC().iterator();
			
			while (itrRespuestaMC.hasNext())
			{
				RespuestaMC respuestaMC = itrRespuestaMC.next();
				respuestaMC.limpiarReferenciasCiclicasPropias();
			}
		}

	@Override
	public String toString() {
		return "PreguntaVF [valorCorrecto=" + valorCorrecto + ", getIdPregunta()=" + getIdPregunta()
				+ ", getPregunta()=" + getPregunta() + "]";
	}

	



}
