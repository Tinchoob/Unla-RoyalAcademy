package datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoDocumento")
public class TipoDocumento {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTipoDocumento;
	private String tipo;
	
	public TipoDocumento(int idTipoDocumento, String tipo) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.tipo = tipo;
	}
	
	public TipoDocumento() {
		super();
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoDocumento [idTipoDocumento=" + idTipoDocumento + ", tipo=" + tipo + "]";
	}
}
