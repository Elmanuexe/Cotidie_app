package salesianos.triana.dam.cotidie.usuario.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDto;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDtoConverter;
import salesianos.triana.dam.cotidie.usuario.model.Role;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.repository.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;


@Service("userDetailsService")
@RequiredArgsConstructor
public class UsuarioAuthService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRegisterDtoConverter useUsuarioRegisterDtoConverter;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        return usuarioRepository.findFirstByNick(nick)
                .orElseThrow(()-> new UsernameNotFoundException(nick + " no encontrado"));
    }

    public Optional<Usuario> findByNick(String nick){
        return usuarioRepository.findFirstByNick(nick);
    }


    public Usuario saveUsuario(UsuarioRegisterDto dto, Role rol){
        return save(useUsuarioRegisterDtoConverter.usuarioDtoToUsuario(dto,rol));
    }

}
