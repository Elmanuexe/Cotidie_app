package salesianos.triana.dam.cotidie.usuario.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.cotidie.usuario.model.Role;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByNick(String nick);

    List<Usuario> findByRole (Role role);


}
