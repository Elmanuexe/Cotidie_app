package salesianos.triana.dam.cotidie.usuario.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserBasicInfoDto {

    private String nombre;
    private String apellidos;
    private String fotoPerfil;
    private UUID id;
}
