package salesianos.triana.dam.cotidie.error.exceptions;

public class EntityNotFoundExceptionCustom extends RuntimeException{
    public EntityNotFoundExceptionCustom(Class clas){
        super (String.format("No se ha encontrado el objeto: "+ clas.getName()));
    }
}
