package salesianos.triana.dam.cotidie.error.exceptions;

public class StorageExceptionCustom extends RuntimeException{
    public StorageExceptionCustom(String message,Exception e){super(message);}
    public StorageExceptionCustom(String message){super(message);}
}
