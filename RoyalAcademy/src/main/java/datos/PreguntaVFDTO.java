package datos;

public class PreguntaVFDTO {
	
	private boolean valorCorrecto;
	private String pregunta;
	private int idMateria;
	
	
	public PreguntaVFDTO(boolean valorCorrecto, String pregunta, int idMateria) {
		super();
		this.valorCorrecto = valorCorrecto;
		this.pregunta = pregunta;
		this.idMateria = idMateria;
	}


	public PreguntaVFDTO() {
		super();
	}


	public boolean isValorCorrecto() {
		return valorCorrecto;
	}


	public void setValorCorrecto(boolean valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}


	public String getPregunta() {
		return pregunta;
	}


	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}


	public int getIdMateria() {
		return idMateria;
	}


	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}


	@Override
	public String toString() {
		return "PreguntaVFDTO [valorCorrecto=" + valorCorrecto + ", pregunta=" + pregunta + ", idMateria=" + idMateria
				+ "]";
	}
	
	

}
