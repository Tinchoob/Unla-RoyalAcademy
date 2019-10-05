package abm;

import org.springframework.data.repository.CrudRepository;

import datos.TipoDocumento;

public interface TipoDocumentoABM extends CrudRepository<TipoDocumento, Integer> {
	
}
