package datos;

public class RespuestavfDTO {
	
	private int idPregunta;
	private boolean valorCorrecto;
	
	
	public RespuestavfDTO() {
		super();
	}


	public int getIdPregunta() {
		return idPregunta;
	}


	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}


	public boolean isValorCorrecto() {
		return valorCorrecto;
	}


	public void setValorCorrecto(boolean valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}


	@Override
	public String toString() {
		return "RespuestavfDTO [idPregunta=" + idPregunta + ", valorCorrecto=" + valorCorrecto + "]";
	}
	
	

}
