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
public class NotaKey implements Serializable {
	  @Column(name = "idExamen")
	    Long idExamen;
	 
	    @Column(name = "idPersona")
	    Long idPersona;
	    
	    

		public NotaKey(Long idExamen, Long idPersona) {
			super();
			this.idExamen = idExamen;
			this.idPersona = idPersona;
		}
		
		
		public NotaKey() {
			super();
		}


		public Long getIdExamen() {
			return idExamen;
		}

		public void setIdExamen(Long idExamen) {
			this.idExamen = idExamen;
		}

		public Long getIdPersona() {
			return idPersona;
		}

		public void setIdPersona(Long idPersona) {
			this.idPersona = idPersona;
		}
		
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idExamen == null) ? 0 : idExamen.hashCode());
			result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
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
			NotaKey other = (NotaKey) obj;
			if (idExamen == null) {
				if (other.idExamen != null)
					return false;
			} else if (!idExamen.equals(other.idExamen))
				return false;
			if (idPersona == null) {
				if (other.idPersona != null)
					return false;
			} else if (!idPersona.equals(other.idPersona))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "NotaKey [idExamen=" + idExamen + ", idPersona=" + idPersona + "]";
		}
	    
	
}
