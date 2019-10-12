package abm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import datos.Persona;

@NoRepositoryBean
public interface PersonaABM<T extends Persona> extends CrudRepository<T, Integer> {
	
}