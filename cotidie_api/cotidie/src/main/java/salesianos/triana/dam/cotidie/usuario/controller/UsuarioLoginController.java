package salesianos.triana.dam.cotidie.usuario.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salesianos.triana.dam.cotidie.security.jwt.JwtManager;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDto;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponse;
import salesianos.triana.dam.cotidie.usuario.dto.auth.UsuarioLoginDtoResponseConverter;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

@RestController
@RequiredArgsConstructor
public class UsuarioLoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final UsuarioLoginDtoResponseConverter usuarioLoginDtoResponseConverter;

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

}
