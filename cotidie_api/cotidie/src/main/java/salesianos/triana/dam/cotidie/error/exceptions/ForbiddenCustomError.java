package salesianos.triana.dam.cotidie.error.exceptions;

public class ForbiddenCustomError extends RuntimeException{
    public ForbiddenCustomError(Class clas){
        super (String.format("No tienes permiso para la acción pedida de la clase: "+ clas.getName()));
    }
}
