package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import abm.TipoDocumentoABM;
import datos.TipoDocumento;

@Controller
@RequestMapping(path="/TipoDocumento")
public class TipoDocumentoControlador {
	
	@Autowired
	private TipoDocumentoABM tipoDocumentoABM;
	
	@PostMapping(path="/add")
	public @ResponseBody String alta(@RequestBody TipoDocumento tipoDocumento) {
		TipoDocumento nTipoDocumento = new TipoDocumento();
		nTipoDocumento.setTipo(tipoDocumento.getTipo());
		tipoDocumentoABM.save(tipoDocumento);
		return "Saved";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String baja(@RequestBody TipoDocumento tipoDocumento) {
		tipoDocumentoABM.delete(tipoDocumento);
		return "Deleted";
	}
	
	@PostMapping(path="/update")
	public @ResponseBody String modificacion(@RequestBody TipoDocumento tipoDocumento) {
		tipoDocumentoABM.save(tipoDocumento);
		return "Updated";
	}
		
	@GetMapping(path="/read")
	public @ResponseBody TipoDocumento traer(@RequestBody TipoDocumento tipoDocumento) {
		return tipoDocumentoABM.findById(tipoDocumento.getIdTipoDocumento()).get();
	}
}
