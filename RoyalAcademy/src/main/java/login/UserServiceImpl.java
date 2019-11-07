package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import abm.UsuarioABM;
import datos.Usuario;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UsuarioABM usuarioABM;
	
    //@Autowired
    //private PermisoABM permisoABM;
    
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
	}

	@Override
	public Usuario findByUsername(String nombreUsuario) {
		return usuarioABM.traerPorNombreUsuario(nombreUsuario);
	}

}
