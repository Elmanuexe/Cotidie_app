package salesianos.triana.dam.cotidie.shared.file.service;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import salesianos.triana.dam.cotidie.error.exceptions.FileEmptyExceptionCustom;
import salesianos.triana.dam.cotidie.error.exceptions.StorageExceptionCustom;
import salesianos.triana.dam.cotidie.shared.file.config.StorageProperties;
import salesianos.triana.dam.cotidie.shared.media_type.MediaTypeUrlResource;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileServiceImp implements FileService{

    private final Path ruta;

    @Autowired
    public FileServiceImp(StorageProperties properties) {
        this.ruta = Paths.get(properties.getLocation());
    }

    @PostConstruct
    @Override
    public void init() throws IOException {
        Files.createDirectories(ruta);
    }

    @Override
    public String saveFile(MultipartFile file,String nombreArchivo) throws IOException {
        if(file.isEmpty()) throw new FileEmptyExceptionCustom(FileEmptyExceptionCustom.class);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.ruta.resolve(nombreArchivo),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        return nombreArchivo;
    }

    public void saveFile (BufferedImage bufferedImage,String nombreArchivo,String extension) throws IOException {
        OutputStream out=Files.newOutputStream(Paths.get("archivos/"+nombreArchivo));
        ImageIO.write(bufferedImage,extension,out);
    }

    public void saveFile(byte[] arrayByte,String nombreArchivo,String extension) throws IOException {
        Files.write(Paths.get("archivos/"+nombreArchivo+"."+extension),arrayByte);
    }

    @Override
    public List<String> saveFileWithCopy(MultipartFile file, int size) throws IOException {
        if(file.isEmpty()) throw new FileEmptyExceptionCustom(FileEmptyExceptionCustom.class);
        String nombreArchivo=generateName(file);
        String extension=StringUtils.getFilenameExtension(file.getOriginalFilename());

        if(file.getContentType().contains("image")){
            String nombreArchivo1=saveFile(file,nombreArchivo+"."+extension);
            BufferedImage bI=resizeImagen(file,1024);
            saveFile(bI,"R_"+nombreArchivo1,extension);
            return List.of(nombreArchivo1,"R_"+nombreArchivo1);
        }
       throw new StorageExceptionCustom("Type of file not supported");
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return ruta.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws MalformedURLException, FileNotFoundException {
        Path file = load(filename);
        MediaTypeUrlResource resource = new MediaTypeUrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        else   throw new FileNotFoundException(
                "Could not read file: " + filename);
    }
    @Override
    public void deleteFile(String filename) throws IOException {
        Path ruta =Paths.get("archivos/"+filename);
        Files.delete(ruta);
    }

    public void deleteListFile(List<String> listaFileNames){
        listaFileNames.forEach(x-> {
            try {
                deleteFile(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public String rescaleAndSaveImagen(MultipartFile file,int size) throws IOException {
        String extension=StringUtils.getFilenameExtension(file.getOriginalFilename());
        String nombreArchivo="R"+generateName(file);
        nombreArchivo+="."+extension;

        BufferedImage bufferedImage= ImageIO.read(file.getInputStream());
        BufferedImage escalado =Scalr.resize(bufferedImage, size);

        OutputStream out=Files.newOutputStream(Paths.get("archivos/"+nombreArchivo));
        ImageIO.write(escalado,extension,out);
        return nombreArchivo;
    }

    private BufferedImage resizeImagen(MultipartFile file,int size) throws IOException {
        BufferedImage bufferedImage= ImageIO.read(file.getInputStream());
        return Scalr.resize(bufferedImage, size);
    }

    public String generateName (MultipartFile file){
        String extension=StringUtils.getFilenameExtension(file.getOriginalFilename());

        Double nuevoNombreDigito = Math.random();
        String nuevoNombre = nuevoNombreDigito.toString().substring(2,10);
        String nombreFinal= nuevoNombre+"."+extension;

        while(Files.exists(ruta.resolve(nombreFinal))){

            nuevoNombreDigito = Math.random();
            nuevoNombre = nuevoNombreDigito.toString().substring(2,10);
            nombreFinal= nuevoNombre+"."+extension;

        }
        return nuevoNombre;
    }

    public String getUri (String fileName){
       return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/resource/")
                .path(fileName)
                .toUriString();
    }

    public String getFileNameOnUrl(String link){
        String []fragmentos=link.split("/");
        link=fragmentos[fragmentos.length-1];
        return link;
    }


}
