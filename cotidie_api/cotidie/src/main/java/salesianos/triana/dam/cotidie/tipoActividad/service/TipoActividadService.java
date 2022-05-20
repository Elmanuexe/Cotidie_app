package salesianos.triana.dam.cotidie.tipoActividad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.ausencia.model.dto.AusenciaDTO;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
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
    private final TipoActividadDTOConverter dtoConverter;


    public TipoActividad save(TipoActividadDTO actividad, Usuario usuario){
        TipoActividad nueva = dtoConverter.convertAusenciaToTIpoActividad(actividad);
        return save(nueva);
    }

    public List<TipoActividadDTO> findAllByFecha(LocalDate fecha){
        List<TipoActividad> ausencias = repository.findAllByFecha(fecha);
        List<TipoActividadDTO> ausenciaDTOS =ausencias.stream().map(x -> dtoConverter.convertAusenciaToDTO(x)).collect(Collectors.toList());
        return ausenciaDTOS;
    }
}
