package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.PreguntaVF;


public interface PreguntaVFABM extends CrudRepository<PreguntaVF, Integer> {
	
	@Modifying
    @Transactional
	@Query(value = "INSERT INTO PreguntaVF (idPregunta) VALUES (:idPregunta)", nativeQuery = true)
	public int registerAsPreguntaVF(@Param("idPregunta")int idPregunta);
}