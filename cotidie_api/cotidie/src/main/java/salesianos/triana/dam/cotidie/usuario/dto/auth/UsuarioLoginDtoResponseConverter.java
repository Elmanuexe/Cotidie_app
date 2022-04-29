package salesianos.triana.dam.cotidie.usuario.dto.auth;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

@Component
public class UsuarioLoginDtoResponseConverter {

    public UsuarioLoginDtoResponse UserAndTokenToUsuarioDtoResponse (Usuario usuario, String token){
        return UsuarioLoginDtoResponse.builder()
                .id(usuario.getId())
                .nick(usuario.getNick())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .rol(usuario.getRole().name())
                .tokenJwt(token)
                .fotoPerfil(usuario.getFotoPerfil())
                .fechaDeNacimiento(usuario.getFechaNaciemiento())
                .build();
    }
}
