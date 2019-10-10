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

public class PreguntaVF extends Pregunta{

	private boolean valorCorrecto;

	
	public PreguntaVF() {
		super();
	}



	public PreguntaVF(int idPregunta, String pregunta) {
		super(idPregunta, pregunta);
		
	}


	public boolean isValorCorrecto() {
		return valorCorrecto;
	}

	public void setValorCorrecto(boolean valorCorrecto) {
		this.valorCorrecto = valorCorrecto;
	}



	@Override
	public String toString() {
		return "PreguntaVF [valorCorrecto=" + valorCorrecto + ", getIdPregunta()=" + getIdPregunta()
				+ ", getPregunta()=" + getPregunta() + "]";
	}

	



}
