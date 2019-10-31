package datos;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idRespuestaExamen")
public class RespuestaExamenMC extends RespuestaExamen {
	private int valor;

	public RespuestaExamenMC(int idRespuestaExamen, Nota nota, int valor) {
		super(idRespuestaExamen, nota);
		this.valor = valor;
	}

	public RespuestaExamenMC() {}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "RespuestaExamenMC [getIdRespuestaExamen()=" + getIdRespuestaExamen() + ", valor=" + valor + "]";
	}
}
