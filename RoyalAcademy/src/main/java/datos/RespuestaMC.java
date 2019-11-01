package datos;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RespuestaMC {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRespuestaMC;
	
	private String respuesta;

	@ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
				fetch = FetchType.LAZY, mappedBy = "lstRespuestaMC")
	private Set<PreguntaMC> lstPreguntaMC;
	
	public RespuestaMC(int idRespuestaMC, String respuesta) {
		super();
		this.idRespuestaMC = idRespuestaMC;
		this.respuesta = respuesta;
	}

	public RespuestaMC() {}
	
	public int getIdRespuestaMC() {
		return idRespuestaMC;
	}

	public void setIdRespuestaMC(int idRespuestaMC) {
		this.idRespuestaMC = idRespuestaMC;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Set<PreguntaMC> getLstPreguntaMC() {
		return lstPreguntaMC;
	}

	public void setLstPreguntaMC(Set<PreguntaMC> lstPreguntaMC) {
		this.lstPreguntaMC = lstPreguntaMC;
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasPropias() {
		this.getLstPreguntaMC().clear();
	}
	
	//Rutina para romper bucle infinito en la serialización
	public void limpiarReferenciasCiclicasExternas() {
		Iterator<PreguntaMC> itrPreguntaMC = this.getLstPreguntaMC().iterator();
		
		while (itrPreguntaMC.hasNext()) {
			PreguntaMC preguntaMC = itrPreguntaMC.next();
			preguntaMC.limpiarReferenciasCiclicasPropias();
		}
	}

	@Override
	public String toString() {
		return "RespuestaMC [idRespuestaMC=" + idRespuestaMC + ", respuesta=" + respuesta + ", lstPreguntaMC="
				+ lstPreguntaMC + "]";
	}
}
