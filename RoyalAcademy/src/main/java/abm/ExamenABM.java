package abm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.Examen;

public interface ExamenABM extends CrudRepository<Examen, Integer> {
	
	@Query(value = "SELECT * FROM EXAMEN E WHERE E.IDCURSADA = :idCursada AND E.IDTURNO = :idTurno",nativeQuery = true)
	public List<Examen> getByCursadaAndTurno(@Param("idCursada") int idCursada,@Param("idTurno") int id);
	
}