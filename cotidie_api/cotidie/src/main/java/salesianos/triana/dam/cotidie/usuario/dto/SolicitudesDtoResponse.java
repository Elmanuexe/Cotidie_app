package salesianos.triana.dam.cotidie.usuario.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class SolicitudesDtoResponse {

    private String nick;
    private int numeroPeticionesPendientes;
}
