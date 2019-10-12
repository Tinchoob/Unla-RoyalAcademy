package abm;

import org.springframework.data.repository.CrudRepository;

import datos.Nota;
import datos.NotaKey;

public interface NotaABM extends CrudRepository<Nota, NotaKey> {
	

}