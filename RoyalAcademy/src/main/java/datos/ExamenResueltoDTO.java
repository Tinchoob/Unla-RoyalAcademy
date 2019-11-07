package datos;

import java.util.List;

public class ExamenResueltoDTO {
	
	private List<RespuestavfDTO> respuestasvf;
	private List<RespuestamcDTO> respuestasmc;
	private int documento;
	private int idExamen;
	
	
	public ExamenResueltoDTO() {
		super();
	}
	
	public List<RespuestavfDTO> getRespuestasvf() {
		return respuestasvf;
	}
	public void setRespuestasvf(List<RespuestavfDTO> respuestasvf) {
		this.respuestasvf = respuestasvf;
	}
	public List<RespuestamcDTO> getRespuestasmc() {
		return respuestasmc;
	}
	public void setRespuestasmc(List<RespuestamcDTO> respuestasmc) {
		this.respuestasmc = respuestasmc;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public int getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}


	@Override
	public String toString() {
		return "ExamenResueltoDTO [respuestasvf=" + respuestasvf + ", respuestasmc=" + respuestasmc + ", documento="
				+ documento + ", idExamen=" + idExamen + "]";
	}
	
	

}
