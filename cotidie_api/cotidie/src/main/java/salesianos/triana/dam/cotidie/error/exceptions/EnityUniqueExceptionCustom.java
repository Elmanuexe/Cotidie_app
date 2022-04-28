package salesianos.triana.dam.cotidie.error.exceptions;

public class EnityUniqueExceptionCustom extends RuntimeException{
    public EnityUniqueExceptionCustom (Class clas){
        super (String.format("No se pueden repetir las: "+ clas.getName()));
    }
}
