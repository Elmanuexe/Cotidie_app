package salesianos.triana.dam.cotidie.tipoActividad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TipoActividadRepository extends JpaRepository<TipoActividad, UUID> {

    @Query("SELECT a FROM TipoActividad a WHERE a.fecha=:fecha")
    List<TipoActividad> findAllByFecha(@Param("fecha") LocalDate fecha);
}
