package salesianos.triana.dam.cotidie.shared.file.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

    void init() throws IOException;

    String saveFile(MultipartFile file,String nombreArchivo) throws IOException;

    List<String> saveFileWithCopy(MultipartFile file,int size) throws IOException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename) throws MalformedURLException, FileNotFoundException;

    void deleteFile(String filename) throws IOException;

    void deleteListFile(List<String> listFilename);

    String rescaleAndSaveImagen(MultipartFile file,int size) throws IOException;

    String getUri(String nameFile);

    String getFileNameOnUrl(String link);

    String generateName (MultipartFile file);

}
