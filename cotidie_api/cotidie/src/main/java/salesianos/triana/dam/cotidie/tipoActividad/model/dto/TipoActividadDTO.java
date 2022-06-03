package salesianos.triana.dam.cotidie.tipoActividad.model.dto;

import lombok.*;
import salesianos.triana.dam.cotidie.tipoActividad.model.Tipo;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoActividadDTO {

    private Tipo tipo;
    private LocalDate fecha;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Boolean todoElDia;
    private UserBasicInfoDto usuario;

    @Override
    public String toString() {
        return "TipoActividadDTO{" +
                "tipo=" + tipo +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFIn=" + horaFin +
                ", todoElDia=" + todoElDia +
                ", usuario=" + usuario +
                '}';
    }
}
