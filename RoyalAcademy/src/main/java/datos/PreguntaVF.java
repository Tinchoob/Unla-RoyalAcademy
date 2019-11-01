package datos;

import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPregunta")
public class PreguntaVF extends Pregunta {

	private boolean valorCorrecto;

	public PreguntaVF() {}
	
	public PreguntaVF(int idPregunta, String pregunta,boolean valorCorrecto) {
		super(idPregunta,pregunta);
		this.valorCorrecto = valorCorrecto;
	}
	
	public boolean isValorCorrecto() {
		return valorCorrecto;
	}
	
	public void setValorCorrecto(boolean valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.getLstExamen().clear();
		this.setMateria(null);
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias(boolean mantenerRespuestas) {
		this.limpiarReferenciasCiclicasPropias();
	}

	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas() {
		Iterator<Examen> itrExamen = this.getLstExamen().iterator();

		this.getMateria().limpiarReferenciasCiclicasPropias();
		while (itrExamen.hasNext())
		{
			Examen examen = itrExamen.next();
			examen.limpiarReferenciasCiclicasPropias();
		}
	}
	
	@Override
	public String toString() {
		return "PreguntaVF [getIdPregunta()=" + getIdPregunta() + ", getPregunta()=" + getPregunta() + ", isActiva()="
				+ isActiva() + ", valorCorrecto=" + valorCorrecto + "]";
	}
}
