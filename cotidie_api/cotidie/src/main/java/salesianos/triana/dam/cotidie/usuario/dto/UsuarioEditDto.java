package salesianos.triana.dam.cotidie.usuario.dto;

import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEditDto {

    private String nombre;
    private String apellidos;
    @Email
    private String email;

}
