package datos;

import java.util.List;
import java.util.Set;

public class PreguntaMCDTO {

	private String pregunta;
	private int valorCorrecto;
	private boolean activa;
	private Set<RespuestaMC> lstRespuestaMC;
	private int idMateria;
	
	
	public PreguntaMCDTO(String pregunta, int valorCorrecto, boolean activa, Set<RespuestaMC> lstRespuestaMC,
			int idMateria) {
		super();
		this.pregunta = pregunta;
		this.valorCorrecto = valorCorrecto;
		this.activa = activa;
		this.lstRespuestaMC = lstRespuestaMC;
		this.idMateria = idMateria;
	}
	

	public PreguntaMCDTO() {
		super();
	}

	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public int getValorCorrecto() {
		return valorCorrecto;
	}


	public void setValorCorrecto(int valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}


	public boolean isActiva() {
		return activa;
	}


	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	public Set<RespuestaMC> getLstRespuestaMC() {
		return lstRespuestaMC;
	}


	public void setLstRespuestaMC(Set<RespuestaMC> lstRespuestaMC) {
		this.lstRespuestaMC = lstRespuestaMC;
	}


	public int getIdMateria() {
		return idMateria;
	}


	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	
	

	@Override
	public String toString() {
		return "PreguntaMCDTO [pregunta=" + pregunta + ", valorCorrecto=" + valorCorrecto + ", activa=" + activa
				+ ", lstRespuestaMC=" + lstRespuestaMC + ", idMateria=" + idMateria + "]";
	}
	
	
	
}
