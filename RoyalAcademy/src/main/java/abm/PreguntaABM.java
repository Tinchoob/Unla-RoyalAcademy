package abm;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import datos.Pregunta;

//@NoRepositoryBean
public interface PreguntaABM<T extends Pregunta> extends CrudRepository<T, Integer> {
	
	//La beta del gta4 estaba mas optimizada que esta query pero que se le va a hacer
	//@Query(value = "SELECT * FROM PREGUNTA P INNER JOIN MATERIA M ON P.IDMATERIA = M.IDMATERIA INNER JOIN EXAMENPREGUNTA EP ON P.IDPREGUNTA = EP.IDPREGUNTA INNER JOIN EXAMEN E ON E.IDEXAMEN = EP.IDEXAMEN WHERE P.IDMATERIA = :idMateria ORDER BY RAND() LIMIT :cantidad", nativeQuery = true)
	@Query("from Pregunta p where p.materia.idMateria=:idMateria order by rand()")
	public List<Pregunta> traerAleatorias(@Param("idMateria")int idMateria);
}
