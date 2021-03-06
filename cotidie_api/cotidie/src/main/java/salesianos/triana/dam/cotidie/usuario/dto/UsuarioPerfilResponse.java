package salesianos.triana.dam.cotidie.usuario.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@Builder
public class UsuarioPerfilResponse {

    private UUID id;
    private String nick,nombre,apellidos,email;
    private String fotoPerfil;
    private LocalDate fechaNaciemiento;

}
