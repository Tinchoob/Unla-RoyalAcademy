package datos;

import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Embeddable
public class ExamenKey implements Serializable {
	  @Column(name = "idExamen")
	    Long idExamen;
	 
	    @Column(name = "idCursada")
	    Long idCursada;
	    
	    @Column(name = "idTurno")
	    Long idTurno;

		public ExamenKey(Long idExamen, Long idCursada, Long idTurno) {
			super();
			this.idExamen = idExamen;
			this.idCursada = idCursada;
			this.idTurno = idTurno;
		}

		public ExamenKey() {
			super();
		}

		public Long getIdExamen() {
			return idExamen;
		}

		public void setIdExamen(Long idExamen) {
			this.idExamen = idExamen;
		}

		public Long getIdCursada() {
			return idCursada;
		}

		public void setIdCursada(Long idCursada) {
			this.idCursada = idCursada;
		}

		public Long getIdTurno() {
			return idTurno;
		}

		public void setIdTurno(Long idTurno) {
			this.idTurno = idTurno;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idCursada == null) ? 0 : idCursada.hashCode());
			result = prime * result + ((idExamen == null) ? 0 : idExamen.hashCode());
			result = prime * result + ((idTurno == null) ? 0 : idTurno.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ExamenKey other = (ExamenKey) obj;
			if (idCursada == null) {
				if (other.idCursada != null)
					return false;
			} else if (!idCursada.equals(other.idCursada))
				return false;
			if (idExamen == null) {
				if (other.idExamen != null)
					return false;
			} else if (!idExamen.equals(other.idExamen))
				return false;
			if (idTurno == null) {
				if (other.idTurno != null)
					return false;
			} else if (!idTurno.equals(other.idTurno))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ExamenKey [idExamen=" + idExamen + ", idCursada=" + idCursada + ", idTurno=" + idTurno + "]";
		}
	    
	    	


	
	    
	
}
