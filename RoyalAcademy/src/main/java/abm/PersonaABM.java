package abm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import datos.Persona;

@NoRepositoryBean
public interface PersonaABM<T extends Persona> extends CrudRepository<T, Integer> {
	
	@Query(value = "SELECT * FROM PERSONA P WHERE P.numeroDocumento = :documento", nativeQuery = true)
	public Persona getBydni(@Param("documento")int documento);
}