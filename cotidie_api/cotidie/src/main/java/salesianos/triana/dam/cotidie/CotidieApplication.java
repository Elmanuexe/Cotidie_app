package salesianos.triana.dam.cotidie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import salesianos.triana.dam.cotidie.shared.file.config.StorageProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class CotidieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotidieApplication.class, args);
	}

}
