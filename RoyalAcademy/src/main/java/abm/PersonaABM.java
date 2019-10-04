package abm;

import org.springframework.data.repository.CrudRepository;
import datos.Persona;

public interface PersonaABM extends CrudRepository<Persona, Integer> {
	
}