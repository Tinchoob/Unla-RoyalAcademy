package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.PreguntaMC;




public interface PreguntaMCABM extends CrudRepository<PreguntaMC, Integer> {
	
	
	@Modifying
    @Transactional
	@Query(value = "INSERT INTO PreguntaMC (idPregunta) VALUES (:idPregunta)", nativeQuery = true)
	public int registerAsPreguntaMC(@Param("idPregunta")int idPregunta);
}