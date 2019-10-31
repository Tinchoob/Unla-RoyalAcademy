package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datos.Pregunta;
import datos.PreguntaVF;

@Transactional
public interface PreguntaVFABM extends PreguntaABM<PreguntaVF>{
	
	
	@Query("from Pregunta p where p.idPregunta=:id")
	public Pregunta findAllById(@Param("id") int id);
	
}