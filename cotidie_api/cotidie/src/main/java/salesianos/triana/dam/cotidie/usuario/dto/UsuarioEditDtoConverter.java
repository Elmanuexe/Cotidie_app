package salesianos.triana.dam.cotidie.usuario.dto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

@Component
public class UsuarioEditDtoConverter {

    public Usuario editarUsuario (UsuarioEditDto dto, Usuario u){
        u.setNombre(dto.getNombre()==null?u.getNombre(): dto.getNombre());
        u.setApellidos(dto.getApellidos()==null?u.getApellidos(): dto.getApellidos());
        u.setEmail(dto.getEmail()==null?u.getEmail():dto.getEmail());
        return u;
    }
}
