package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import datos.Alumno;

public interface AlumnoABM extends CrudRepository<Alumno, Integer> {
	
	@Modifying
    @Transactional
	@Query(value = "INSERT INTO Alumno (idPersona) VALUES (:idPersona)", nativeQuery = true)
	public int registerAsAlumno(@Param("idPersona")int idPersona);
}