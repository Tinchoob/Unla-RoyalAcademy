package datos;

import javax.persistence.Embeddable;

import java.io.Serializable;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Embeddable
public class NotaKey implements Serializable {
	@Column(name = "idExamen")
	int idExamen;

	@Column(name = "idPersona")
	int idPersona;
	
	public NotaKey(int idExamen, int idPersona) {
		super();
		this.idExamen = idExamen;
		this.idPersona = idPersona;
	}

	public NotaKey() {}

	public int getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idExamen == null) ? 0 : idExamen.hashCode());
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		NotaKey other = (NotaKey)obj;
		
		if (this.idExamen == null && other.idExamen != null) return false;
		else if (!this.idExamen.equals(other.idExamen)) return false;
		
		if (this.idPersona == null && other.idPersona != null) return false;
		else if (!this.idPersona.equals(other.idPersona))return false;
		
		return true;
	}*/


	@Override
	public String toString() {
		return "NotaKey [idExamen=" + idExamen + ", idPersona=" + idPersona + "]";
	}
}
