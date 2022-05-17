package salesianos.triana.dam.cotidie.usuario.dto.auth;

import lombok.*;
import salesianos.triana.dam.cotidie.usuario.validation.anotation.SamePassword;
import salesianos.triana.dam.cotidie.usuario.validation.anotation.UniqueField;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SamePassword(password1 = "password", password2 = "password2")
public class UsuarioRegisterDto {

    @UniqueField(message = "{nick.name.unique}")
    private String nick;
    @NotEmpty(message = "{field.can.not.be.null}")
    private String nombre;
    @NotEmpty(message = "{field.can.not.be.null}")
    private String apellidos;
    @Email(message = "{email.format}")
    private String email;
    private String password;
    private String password2;
    private String fotoPerfil;
    private String telefono;

}
