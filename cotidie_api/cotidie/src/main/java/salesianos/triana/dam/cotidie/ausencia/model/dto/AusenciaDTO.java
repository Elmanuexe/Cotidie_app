package salesianos.triana.dam.cotidie.ausencia.model.dto;

import lombok.*;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AusenciaDTO {

    private LocalDate fecha;
    private String detalle;
    private UserBasicInfoDto usuario;
}
