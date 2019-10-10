package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.Examen;

public interface ExamenABM extends CrudRepository<Examen, Integer> {
	

}