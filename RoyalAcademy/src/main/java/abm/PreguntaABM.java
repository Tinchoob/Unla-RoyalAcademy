package abm;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import datos.Pregunta;

//@NoRepositoryBean
public interface PreguntaABM<T extends Pregunta> extends CrudRepository<T, Integer> {
	
	@Query(value = "SELECT * FROM Pregunta WHERE idMateria=:idMateria ORDER BY RAND() LIMIT :cantidad", nativeQuery = true)
	public Set<Pregunta> traerAleatorias(@Param("idMateria")int idMateria, @Param("cantidad")int cantidad);
}
