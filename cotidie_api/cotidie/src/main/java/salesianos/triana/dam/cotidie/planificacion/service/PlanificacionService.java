package salesianos.triana.dam.cotidie.planificacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;
import salesianos.triana.dam.cotidie.planificacion.model.dto.PlanDTO;
import salesianos.triana.dam.cotidie.planificacion.model.dto.PlanDTOConverter;
import salesianos.triana.dam.cotidie.planificacion.repository.PlanificacionRepository;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.impl.UsuarioServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanificacionService extends BaseService<PlanificacionMensual, UUID, PlanificacionRepository> {

    private final PlanificacionRepository repository;
    private final PlanDTOConverter dtoConverter;
    private final UsuarioServiceImp usuario;

    public boolean existeByMes(LocalDate mes, UUID id) {
        if (repository.existePorMes(mes.getMonthValue(), id).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public PlanificacionMensual findPorMes(LocalDate mes, UUID uuid) {
        return repository.findPorMes(mes.getMonthValue(), uuid);
    }

    public PlanDTO findDTOPorMes(LocalDate mes, UUID uuid) {
        if (!existeByMes(mes, uuid)){
            saveFromMes(mes, uuid);
        }
        PlanificacionMensual plan = repository.findPorMes(mes.getMonthValue(), uuid);
        PlanDTO planDTO = dtoConverter.convertPlanToPlanDTO(plan);
        return planDTO;
    }

    public PlanificacionMensual saveFromActividad(TipoActividad actividad, Usuario usuario) {
        LocalDate primeroMes = LocalDate.of(actividad.getFecha().getYear(), actividad.getFecha().getMonth(), 1);
        return repository.save(PlanificacionMensual.builder()
                .fechaInicio(primeroMes)
                .usuario(usuario)
                .eventos(new ArrayList<>())
                .build());
    }

    public PlanificacionMensual saveFromMes(LocalDate fecha, UUID uuid) {
        LocalDate primeroMes = LocalDate.of(fecha.getYear(), fecha.getMonth(), 1);
        Usuario u = usuario.findById(uuid).get();
        return repository.save(PlanificacionMensual.builder()
                .fechaInicio(primeroMes)
                .usuario(u)
                .eventos(new ArrayList<>())
                .build());
    }


}
