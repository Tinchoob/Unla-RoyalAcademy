package abm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datos.Usuario;


@Transactional
public interface UsuarioABM extends PersonaABM<Usuario> {
	
	@Query(value = "SELECT * FROM Usuario WHERE nombreUsuario=:nombreUsuario", nativeQuery = true)
	Usuario traerPorNombreUsuario(@Param("nombreUsuario")String nombreUsuario);
}