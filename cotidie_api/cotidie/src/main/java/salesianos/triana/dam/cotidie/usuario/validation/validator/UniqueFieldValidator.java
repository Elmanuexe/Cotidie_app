package salesianos.triana.dam.cotidie.usuario.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import salesianos.triana.dam.cotidie.usuario.service.auth.UsuarioAuthService;
import salesianos.triana.dam.cotidie.usuario.validation.anotation.UniqueField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator  implements ConstraintValidator<UniqueField,String> {

    @Autowired
    UsuarioAuthService usuarioAuthService;

    @Override
    public void initialize(UniqueField constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return usuarioAuthService.findByNick(value).isEmpty();
    }
}
