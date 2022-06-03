package salesianos.triana.dam.cotidie.tipoActividad.model.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDtoConverter;

@Component
@RequiredArgsConstructor
public class TipoActividadDTOConverter {

    private final UserBasicInfoDtoConverter userBasicInfoDtoConverter;

    public TipoActividadDTO convertAusenciaToDTO (TipoActividad a){
        return TipoActividadDTO.builder()
                .fecha(a.getFecha())
                .horaInicio(a.getHoraInicio())
                .horaFin(a.getHoraFIn())
                .descripcion(a.getDescripcion())
                .usuario(userBasicInfoDtoConverter.userToUserBasicInfoDto(a.getPlanificacion().getUsuario()))
                .tipo(a.getTipo())
                .build();
    }



    public TipoActividad convertAusenciaToTIpoActividad(TipoActividadDTO dto){
        return TipoActividad.builder()
                .descripcion(dto.getDescripcion())
                .horaInicio(dto.getHoraInicio())
                .horaFIn(dto.getHoraFin())
                .tipo(dto.getTipo())
                .fecha(dto.getFecha())
                .build();
    }
}
