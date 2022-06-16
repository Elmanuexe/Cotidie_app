package salesianos.triana.dam.cotidie.tipoActividad.model.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import salesianos.triana.dam.cotidie.tipoActividad.model.Tipo;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicActividadDTO implements Comparable<BasicActividadDTO>{

    private UUID id;
    private Tipo tipo;
    private LocalDate fecha;
    private String descripcion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Boolean todoElDia;

    @Override
    public int compareTo(BasicActividadDTO o) {
        return getFecha().compareTo(o.getFecha());
    }
}
