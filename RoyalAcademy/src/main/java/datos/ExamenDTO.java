package datos;

import java.util.Arrays;

public class ExamenDTO {

	private int idCursada;
	private int idTurno;
	private int[] idPreguntasSeleccionadas;
	
	
	public ExamenDTO(int idCursada, int idTurno, int[] idPreguntasSeleccionadas) {
		super();
		this.idCursada = idCursada;
		this.idTurno = idTurno;
		this.idPreguntasSeleccionadas = idPreguntasSeleccionadas;
	}


	public ExamenDTO() {
		super();
	}


	public int getIdCursada() {
		return idCursada;
	}


	public void setIdCursada(int idCursada) {
		this.idCursada = idCursada;
	}


	public int getIdTurno() {
		return idTurno;
	}


	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}


	public int[] getIdPreguntasSeleccionadas() {
		return idPreguntasSeleccionadas;
	}


	public void setIdPreguntasSeleccionadas(int[] idPreguntasSeleccionadas) {
		this.idPreguntasSeleccionadas = idPreguntasSeleccionadas;
	}


	@Override
	public String toString() {
		return "ExamenDTO [idCursada=" + idCursada + ", idTurno=" + idTurno + ", idPreguntasSeleccionadas="
				+ Arrays.toString(idPreguntasSeleccionadas) + "]";
	}
	
	
	
	
	
	
}
