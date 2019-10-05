package abm;

import javax.transaction.Transactional;

import datos.Usuario;


@Transactional
public interface UsuarioABM extends PersonaABM<Usuario> {
	
}