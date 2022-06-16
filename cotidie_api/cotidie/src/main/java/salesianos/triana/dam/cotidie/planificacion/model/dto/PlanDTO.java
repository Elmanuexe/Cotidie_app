package salesianos.triana.dam.cotidie.planificacion.model.dto;

import lombok.*;
import salesianos.triana.dam.cotidie.tipoActividad.model.TipoActividad;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.BasicActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTO;
import salesianos.triana.dam.cotidie.usuario.dto.UserBasicInfoDto;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<BasicActividadDTO> eventos = new ArrayList<>();
    private UserBasicInfoDto usuario;
}
