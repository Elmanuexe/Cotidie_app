package salesianos.triana.dam.cotidie.tipoActividad.model.dto;

import lombok.*;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoActividadDTO {

    private Enum tipo;
    private LocalDate fecha;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFIn;
    private Boolean todoElDia;
    private UserBasicInfoDto usuario;
}
