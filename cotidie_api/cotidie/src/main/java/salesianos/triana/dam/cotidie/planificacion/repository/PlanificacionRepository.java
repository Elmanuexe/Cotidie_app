package salesianos.triana.dam.cotidie.planificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;

import java.util.Optional;
import java.util.UUID;

public interface PlanificacionRepository extends JpaRepository<PlanificacionMensual, UUID> {

    @Query("SELECT a FROM PlanificacionMensual a WHERE to_char(month(a.fechaInicio),'MM') = to_char(:fecha, 'MM') AND a.usuario.id=:id_usuario")
    Optional<PlanificacionMensual> existePorMes(@Param("fecha")Integer fecha, @Param("id_usuario") UUID uuid);

    @Query("SELECT a FROM PlanificacionMensual a WHERE to_char(month(a.fechaInicio),'MM') = to_char(:fecha, 'MM') AND a.usuario.id=:id_usuario")
    PlanificacionMensual findPorMes(@Param("fecha")Integer fecha, @Param("id_usuario") UUID uuid);
}
