package datos;

public class RespuestamcDTO {
	
	private int idPregunta;
	private int valorCorrecto;
	
	
	public RespuestamcDTO() {
		super();
	}


	public int getIdPregunta() {
		return idPregunta;
	}


	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}


	public int getValorCorrecto() {
		return valorCorrecto;
	}


	public void setValorCorrecto(int valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}


	@Override
	public String toString() {
		return "RespuestamcDTO [idPregunta=" + idPregunta + ", valorCorrecto=" + valorCorrecto + "]";
	}
	
	

}
