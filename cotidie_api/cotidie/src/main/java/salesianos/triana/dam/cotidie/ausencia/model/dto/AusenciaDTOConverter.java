package salesianos.triana.dam.cotidie.ausencia.model.dto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDtoConverter;

@Component
@AllArgsConstructor
public class AusenciaDTOConverter {

    private final UserBasicInfoDtoConverter userBasicInfoDtoConverter;

    public AusenciaDTO convertAusenciaToDTO (Ausencia a){
        return AusenciaDTO.builder()
                .fecha(a.getFecha())
                .detalle(a.getDetalle())
                .usuario(userBasicInfoDtoConverter.userToUserBasicInfoDto(a.getUsuario()))
                .build();
    }

    public Ausencia convertDTOToAusencia (AusenciaDTO a){
        return Ausencia.builder()
                .fecha(a.getFecha())
                .detalle(a.getDetalle())
                .build();
    }
}
