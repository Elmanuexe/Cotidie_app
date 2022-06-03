package salesianos.triana.dam.cotidie.tipoActividad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TipoActividadRepository extends JpaRepository<TipoActividad, UUID> {

    @Query("SELECT a FROM TipoActividad a WHERE a.fecha=:fecha AND a.tipo = 2")
    List<TipoActividad> findAllAusenciasByFecha(@Param("fecha") LocalDate fecha);

    @Query(value = "SELECT * FROM tipo_actividad a JOIN planificacion p ON a.planificacion_id = p.id JOIN usuario u ON p.usuario_id = u.id WHERE u.id=:usuario_id AND a.tipo= 2", nativeQuery = true)
    List<TipoActividad> findAllAusenciasByUsuario(@Param("usuario_id") UUID usuario_id);
}
