package salesianos.triana.dam.cotidie.usuario.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import salesianos.triana.dam.cotidie.security.jwt.JwtManager;
import salesianos.triana.dam.cotidie.shared.file.service.FileService;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDto;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponseConverter;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioRegisterDto;
import salesianos.triana.dam.cotidie.usuario.model.Role;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.auth.UsuarioAuthService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final UsuarioLoginDtoResponseConverter usuarioLoginDtoResponseConverter;
    private final FileService fileService;
    private final UsuarioAuthService usuarioAuthService;

    @CrossOrigin
    @PostMapping("/login")
    public UsuarioLoginDtoResponse login(@RequestBody UsuarioLoginDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getNick(),
                                dto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtManager.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return usuarioLoginDtoResponseConverter.UserAndTokenToUsuarioDtoResponse(usuario,jwt);
    }

    @GetMapping("/me")
    public UsuarioLoginDtoResponse howAmI(@AuthenticationPrincipal Usuario usuario){
        return usuarioLoginDtoResponseConverter.UserAndTokenToUsuarioDtoResponse(usuario,null);
    }

    @PostMapping("/register/user")
    public ResponseEntity<UsuarioLoginDtoResponse> registerAndLoginUser(@Valid @RequestPart ("usuario") UsuarioRegisterDto dto,
                                                                    @RequestPart ("file") MultipartFile file) throws IOException {

        String nombreArchivo=fileService.rescaleAndSaveImagen(file,124);
        String uri = fileService.getUri(nombreArchivo);
        dto.setFotoPerfil(uri);

        Usuario u= usuarioAuthService.saveUsuario(dto, Role.USER);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                u.getNick(),
                                dto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtManager.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioLoginDtoResponseConverter.UserAndTokenToUsuarioDtoResponse(usuario,jwt));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UsuarioLoginDtoResponse> registerAndLoginAdmin(@Valid @RequestPart ("usuario") UsuarioRegisterDto dto,
                                                                    @RequestPart ("file") MultipartFile file) throws IOException {

        String nombreArchivo=fileService.rescaleAndSaveImagen(file,124);
        String uri = fileService.getUri(nombreArchivo);
        dto.setFotoPerfil(uri);

        Usuario u= usuarioAuthService.saveUsuario(dto, Role.ADMIN);

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                u.getNick(),
                                dto.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtManager.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioLoginDtoResponseConverter.UserAndTokenToUsuarioDtoResponse(usuario,jwt));
    }
}
