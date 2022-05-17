package salesianos.triana.dam.cotidie.dia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.cotidie.dia.model.Dia;

import java.util.UUID;

public interface DiaRepository extends JpaRepository<Dia, UUID> {

}
