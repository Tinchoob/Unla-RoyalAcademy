package abm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import datos.Pregunta;

@NoRepositoryBean
public interface PreguntaABM<T extends Pregunta> extends CrudRepository<T, Integer> {

}
