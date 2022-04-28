package salesianos.triana.dam.cotidie.error.exceptions;

public class FileEmptyExceptionCustom extends RuntimeException{
    public FileEmptyExceptionCustom(Class clas){
        super (String.format("Fichero vacio: "+ clas.getName()));
    }
}
