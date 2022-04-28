package salesianos.triana.dam.cotidie.usuario.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import salesianos.triana.dam.cotidie.error.exceptions.EntityNotFoundExceptionCustom;
import salesianos.triana.dam.cotidie.shared.file.service.FileService;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDtoConverter;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioEditDto;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioEditDtoConverter;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioPerfilResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDto;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDtoConverter;
import salesianos.triana.dam.cotidie.usuario.model.Role;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.repository.UsuarioRepository;
import salesianos.triana.dam.cotidie.usuario.service.UsuarioService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRegisterDtoConverter usuarioRegisterDtoConverter;
    private final FileService fileService;
    private final UsuarioEditDtoConverter usuarioEditDtoConverter;
    private final UserBasicInfoDtoConverter userBasicInfoDtoConverter;


    @Override
    public UsuarioPerfilResponse perfilDeUsuario(Usuario usuario, UUID id) {
        Optional<Usuario> usuarioQueQuieroVerOpt=usuarioRepository.findById(id);
        if(usuarioQueQuieroVerOpt.isPresent()) {
            Usuario usuarioQueQuierover=usuarioQueQuieroVerOpt.get();
                return UsuarioPerfilResponse.builder()
                        .nick(usuarioQueQuierover.getNick())
                        .id(usuarioQueQuierover.getId())
                        .nombre(usuarioQueQuierover.getNombre())
                        .apellidos(usuarioQueQuierover.getApellidos())
                        .email(usuarioQueQuierover.getEmail())
                        .fotoPerfil(usuarioQueQuierover.getFotoPerfil())
                        .fechaNaciemiento(usuarioQueQuierover.getFechaNaciemiento())
                        .build();
        }
        throw new EntityNotFoundExceptionCustom(Usuario.class);
    }


    @Override
    public Usuario save(UsuarioRegisterDto dto, MultipartFile file) throws IOException {
        String fileName=fileService.generateName(file);
        String ruta=fileService.saveFile(file,fileName);
        Usuario u=usuarioRegisterDtoConverter.usuarioDtoToUsuario(dto, Role.USER);
        u.setFotoPerfil(ruta);
        return usuarioRepository.save(u);
    }

    @Override
    public Usuario save(UsuarioEditDto dto, MultipartFile file, Usuario usuario) throws IOException {
        fileService.deleteFile(fileService.getFileNameOnUrl(usuario.getFotoPerfil()));
        String nombreArchivo=fileService.rescaleAndSaveImagen(file,124);
        String uri = fileService.getUri(nombreArchivo);
        Usuario u=usuarioEditDtoConverter.editarUsuario(dto,usuario);
        u.setFotoPerfil(uri);
        u.setId(usuario.getId());
        return usuarioRepository.save(u);
    }


}
