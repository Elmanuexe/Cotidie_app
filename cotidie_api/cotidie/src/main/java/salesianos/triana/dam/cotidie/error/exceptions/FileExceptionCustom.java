package salesianos.triana.dam.cotidie.error.exceptions;

import io.jsonwebtoken.io.IOException;

public class FileExceptionCustom extends IOException {
    public FileExceptionCustom (Class clas){
        super(String.format("Error de archivo "+ clas.getName()));
    }
}
