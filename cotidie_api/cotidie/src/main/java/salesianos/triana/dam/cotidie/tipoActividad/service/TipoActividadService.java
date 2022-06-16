package salesianos.triana.dam.cotidie.tipoActividad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.planificacion.repository.PlanificacionRepository;
import salesianos.triana.dam.cotidie.planificacion.service.PlanificacionService;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.tipoActividad.model.Tipo;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.BasicActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTOConverter;
import salesianos.triana.dam.cotidie.tipoActividad.repository.TipoActividadRepository;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TipoActividadService extends BaseService<TipoActividad, UUID, TipoActividadRepository> {

    private final TipoActividadRepository repository;
    private final PlanificacionService planificacionService;
    private final PlanificacionRepository planificacionRepository;
    private final TipoActividadDTOConverter dtoConverter;

    public TipoActividad saveAusencia(TipoActividadDTO actividad, Usuario usuario){
        TipoActividad nueva = dtoConverter.convertActividadDTOToTipoActividad(actividad);
        nueva.setTipo(Tipo.AUSENCIA);
        if (!planificacionService.existeByMes(actividad.getFecha(), usuario.getId())){
            planificacionService.saveFromActividad(nueva, usuario);
        }
        nueva.addDiaToPlanificacion(planificacionService.findPorMes(actividad.getFecha(), usuario.getId()));
        return save(nueva);
    }

    public TipoActividad saveTurno(TipoActividadDTO actividad, Usuario usuario){
        TipoActividad nueva = dtoConverter.convertActividadDTOToTipoActividad(actividad);
        nueva.setTipo(Tipo.TRABAJO);
        if (!planificacionService.existeByMes(actividad.getFecha(), usuario.getId())){
            planificacionService.saveFromActividad(nueva, usuario);
        }
        nueva.addDiaToPlanificacion(planificacionService.findPorMes(actividad.getFecha(), usuario.getId()));
        return save(nueva);
    }

    public List<TipoActividadDTO> findAllByFecha(LocalDate fecha){
        List<TipoActividad> ausencias = repository.findAllAusenciasByFecha(fecha);
        List<TipoActividadDTO> ausenciaDTOS =ausencias.stream().map(x -> dtoConverter.convertActividadToDTO(x)).collect(Collectors.toList());
        return ausenciaDTOS;
    }

    public List<BasicActividadDTO> findAllAusenciasByUsuario(UUID id){
        List<TipoActividad> ausencias = repository.findAllAusenciasByUsuario(id);
        List<BasicActividadDTO> ausenciaDTOS =ausencias.stream().map(x -> dtoConverter.convertActividadToBasicDTO(x)).collect(Collectors.toList());
        return ausenciaDTOS;
    }

    public List<TipoActividadDTO> findTurnoDeUsuario(UUID id, LocalDate fecha){
        List<TipoActividad> ausencias = repository.findTurnoDeUsuario(id, fecha);
        List<TipoActividadDTO> ausenciaDTOS =ausencias.stream().map(x -> dtoConverter.convertActividadToDTO(x)).collect(Collectors.toList());
        return ausenciaDTOS;
    }
}
