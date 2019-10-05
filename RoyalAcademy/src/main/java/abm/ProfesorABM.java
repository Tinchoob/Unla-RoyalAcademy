package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.Profesor;

public interface ProfesorABM extends CrudRepository<Profesor, Integer> {
	
	@Modifying
    @Transactional
	@Query(value = "INSERT INTO Profesor (idPersona) VALUES (:idPersona)", nativeQuery = true)
	public int registerAsProfesor(@Param("idPersona")int idPersona);
}