package salesianos.triana.dam.cotidie.planificacion.model.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.BasicActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTOConverter;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDtoConverter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanDTOConverter {

    private final UserBasicInfoDtoConverter userBasicInfoDtoConverter;
    private final TipoActividadDTOConverter tipoActividadDTOConverter;

    public PlanDTO convertPlanToPlanDTO(PlanificacionMensual plan){
        List<BasicActividadDTO> listaBasic = plan.getEventos().stream().map(x -> tipoActividadDTOConverter.convertActividadToBasicDTO(x)).collect(Collectors.toList());
        Collections.sort(listaBasic);
        return PlanDTO.builder()
                .fechaFin(plan.getFechaFin())
                .fechaInicio(plan.getFechaInicio())
                .usuario(userBasicInfoDtoConverter.userToUserBasicInfoDto(plan.getUsuario()))
                .eventos(listaBasic)
                .build();
    }

}
