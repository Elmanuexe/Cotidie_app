package salesianos.triana.dam.cotidie.usuario.dto.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.usuario.model.Role;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

@Component
public class UsuarioRegisterDtoConverter {

    @Autowired
    private  PasswordEncoder codificador;

    public Usuario usuarioDtoToUsuario (UsuarioRegisterDto dto, Role role){

        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .email(dto.getEmail())
                .nick(dto.getNick())
                .password(codificador.encode(dto.getPassword()))
                .role(role)
                .fotoPerfil(dto.getFotoPerfil())
                .telefono(dto.getTelefono())
                .build();
    }
}
