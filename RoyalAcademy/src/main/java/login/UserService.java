package login;

import datos.Usuario;

public interface UserService {
	void save(Usuario usuario);
	Usuario findByUsername(String nombreUsuario);
}
