package datos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idRespuestaExamen")
public class RespuestaExamenVF extends RespuestaExamen {
	private boolean valor;

	public RespuestaExamenVF(int idRespuestaExamen, Nota nota, boolean valor) {
		super(idRespuestaExamen, nota);
		this.valor = valor;
	}

	public RespuestaExamenVF() {}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "RespuestaExamenVF [getIdRespuestaExamen()=" + getIdRespuestaExamen() + ", valor=" + valor + "]";
	}
}
