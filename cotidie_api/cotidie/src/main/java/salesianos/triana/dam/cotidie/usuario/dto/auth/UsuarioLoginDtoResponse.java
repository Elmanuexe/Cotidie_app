package salesianos.triana.dam.cotidie.usuario.dto.auth;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UsuarioLoginDtoResponse {

    private UUID id;
    private String nick;
    private String nombre;
    private String apellidos;
    private String rol;
    private String fotoPerfil;
    private String tokenJwt;

}
