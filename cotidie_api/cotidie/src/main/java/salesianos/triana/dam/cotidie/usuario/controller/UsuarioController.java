package salesianos.triana.dam.cotidie.usuario.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salesianos.triana.dam.cotidie.shared.file.service.FileService;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioEditDto;
import salesianos.triana.dam.cotidie.usuario.dto.UsuarioPerfilResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponseConverter;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.UsuarioService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioLoginDtoResponseConverter usuarioLoginDtoResponseConverter;
    private final FileService fileService;

    @GetMapping("/{id}")
    public UsuarioPerfilResponse verPerfil (@AuthenticationPrincipal Usuario usuario, @PathVariable ("id") UUID id){
        return usuarioService.perfilDeUsuario(usuario,id);
    }

    @PutMapping("/me")
    public UsuarioLoginDtoResponse editarMiPerfil (@AuthenticationPrincipal Usuario usuario,
                                                   @Valid @RequestPart ("usuario") UsuarioEditDto dto,
                                                   @RequestPart ("file")MultipartFile file) throws IOException {
        return usuarioLoginDtoResponseConverter.UserAndTokenToUsuarioDtoResponse(usuarioService.save(dto,file,usuario),null);
    }

}
