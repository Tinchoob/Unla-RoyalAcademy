package abm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import datos.RespuestaExamen;

@NoRepositoryBean
public interface RespuestaExamenABM<T extends RespuestaExamen> extends CrudRepository<T, Integer> {

}