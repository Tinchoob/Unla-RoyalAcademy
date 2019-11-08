package repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import datos.Usuario;;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Long>  {
    public Optional<Usuario> findByUsername(String nombreUsuario);
}