package salesianos.triana.dam.cotidie.ausencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AusenciaRepository extends JpaRepository<Ausencia, UUID> {

    @Query("SELECT a FROM Ausencia a WHERE a.fecha=:fecha")
    List<Ausencia> findAllByFecha(@Param("fecha") LocalDate fecha);
}
