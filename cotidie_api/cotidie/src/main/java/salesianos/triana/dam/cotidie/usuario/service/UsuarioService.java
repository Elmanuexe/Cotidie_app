package salesianos.triana.dam.cotidie.usuario.service;

import org.springframework.web.multipart.MultipartFile;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioEditDto;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioPerfilResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDto;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import java.io.IOException;
import java.util.UUID;

public interface UsuarioService {

    UsuarioPerfilResponse perfilDeUsuario(Usuario usuario, UUID id);

    Usuario save(UsuarioRegisterDto usuario, MultipartFile file) throws IOException;

    Usuario save(UsuarioEditDto dto, MultipartFile file, Usuario usuario) throws IOException;

}
