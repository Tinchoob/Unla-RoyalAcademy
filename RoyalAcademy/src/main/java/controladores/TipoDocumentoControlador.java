package controladores;

import java.util.ArrayList;
import java.util.List;

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
	public @ResponseBody List<TipoDocumento> alta(@RequestBody TipoDocumento[] tipoDocumentoArr) {
		List<TipoDocumento> lstTipoDocumentoAgregado = new ArrayList<TipoDocumento>();
		
		for (TipoDocumento tipoDocumento: tipoDocumentoArr) {
			try {
				tipoDocumento.setIdTipoDocumento(0);         //Para evitar que sobreescriba si se le manda algo con ID
				tipoDocumentoABM.save(tipoDocumento);
				lstTipoDocumentoAgregado.add(tipoDocumento);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstTipoDocumentoAgregado;
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody List<TipoDocumento> baja(@RequestBody TipoDocumento[] tipoDocumentoArr) {
		List<TipoDocumento> lstTipoDocumentoEliminado = new ArrayList<TipoDocumento>();
		
		for (TipoDocumento tipoDocumento: tipoDocumentoArr) {
			try {
				tipoDocumentoABM.delete(tipoDocumento);
				lstTipoDocumentoEliminado.add(tipoDocumento);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstTipoDocumentoEliminado;
	}
	
	@PostMapping(path="/update")
	public @ResponseBody List<TipoDocumento> modificacion(@RequestBody TipoDocumento[] tipoDocumentoArr) {
		List<TipoDocumento> lstTipoDocumentoActualizado = new ArrayList<TipoDocumento>();
		
		for (TipoDocumento tipoDocumento: tipoDocumentoArr) {
			try {
				tipoDocumentoABM.save(tipoDocumento);
				lstTipoDocumentoActualizado.add(tipoDocumento);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstTipoDocumentoActualizado;
	}
		
	@GetMapping(path="/read")
	public @ResponseBody List<TipoDocumento> traer(@RequestBody TipoDocumento[] tipoDocumentoArr) {
		List<TipoDocumento> lstTipoDocumento = new ArrayList<TipoDocumento>();
		
		for (TipoDocumento tipoDocumento: tipoDocumentoArr) {
			try {
				lstTipoDocumento.add(tipoDocumentoABM.findById(tipoDocumento.getIdTipoDocumento()).get());
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstTipoDocumento;
	}
	
	@GetMapping(path="/readAll")
	public @ResponseBody List<TipoDocumento> traerTodo() {
		Iterable<TipoDocumento> itrTipoDocumento = tipoDocumentoABM.findAll();
		List<TipoDocumento> lstTipoDocumento = new ArrayList<TipoDocumento>();
		
		for (TipoDocumento tipoDocumento: itrTipoDocumento) {
			try {
				lstTipoDocumento.add(tipoDocumento);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return lstTipoDocumento;
	}
}
