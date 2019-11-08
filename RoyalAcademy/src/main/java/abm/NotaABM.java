package abm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.Examen;
import datos.Nota;
import datos.NotaKey;

public interface NotaABM extends CrudRepository<Nota, NotaKey> {
	

	@Query(value = "SELECT * FROM NOTA N WHERE N.idExamen = :idExamen",nativeQuery = true)
	public List<Nota> geyByIdExamen(@Param("idExamen") int idExamen);
	
}