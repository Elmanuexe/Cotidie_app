package salesianos.triana.dam.cotidie.usuario.dto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

@Component
public class UserBasicInfoDtoConverter {

    public UserBasicInfoDto userToUserBasicInfoDto (Usuario usuario){
        return UserBasicInfoDto.builder()
                .id(usuario.getId())
                .nick(usuario.getNick())
                .fotoPerfil(usuario.getFotoPerfil())
                .build();
    }
}
