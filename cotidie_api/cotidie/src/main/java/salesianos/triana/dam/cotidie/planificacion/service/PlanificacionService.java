package salesianos.triana.dam.cotidie.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;
import salesianos.triana.dam.cotidie.planificacion.repository.PlanificacionRepository;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanificacionService extends BaseService<PlanificacionMensual, UUID, PlanificacionRepository> {

    private final PlanificacionRepository repository;

    public boolean existeByMes(LocalDate mes) {
        if (repository.existePorMes(mes.getMonthValue()).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public PlanificacionMensual findPorMes(LocalDate mes) {
        return repository.findPorMes(mes.getMonthValue());
    }

    public PlanificacionMensual saveFromMes(TipoActividad actividad, Usuario usuario) {
        LocalDate primeroMes = LocalDate.of(actividad.getFecha().getYear(), actividad.getFecha().getMonth(), 1);
        return repository.save(PlanificacionMensual.builder()
                .fechaInicio(primeroMes)
                .usuario(usuario)
                .dias(new ArrayList<>())
                .build());
    }


}
