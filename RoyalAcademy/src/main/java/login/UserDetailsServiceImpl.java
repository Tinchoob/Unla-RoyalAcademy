package login;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import abm.UsuarioABM;
import datos.Permiso;
import datos.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UsuarioABM usuarioABM;
	
	@Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Usuario usuario = usuarioABM.traerPorNombreUsuario(username);
        if (usuario == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permiso permiso : usuario.getLstPermiso()){
            grantedAuthorities.add(new SimpleGrantedAuthority(permiso.getNombre()));
        }

        return new org.springframework.security.core.userdetails.User(usuario.getNombre(), usuario.getContraseña(), grantedAuthorities);
	}
}
