package salesianos.triana.dam.cotidie.tipoActividad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.BasicActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTOConverter;
import salesianos.triana.dam.cotidie.tipoActividad.service.TipoActividadService;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.impl.UsuarioServiceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TipoActividadController {

    private final TipoActividadDTOConverter dtoConverter;
    private final TipoActividadService service;
    private final UsuarioServiceImp usuarioService;

    //@PostMapping("/dia/{id}")
    @PostMapping("/ausencia/dia")
    private BasicActividadDTO nuevaAusencia(@AuthenticationPrincipal Usuario usuario, @RequestBody TipoActividadDTO dto){
        Usuario user = usuarioService.findById(usuario.getId()).get();
        return dtoConverter.convertActividadToBasicDTO(service.saveAusencia(dto, user));
    }

    @CrossOrigin
    @PostMapping("/turno/dia/{id}")
    private BasicActividadDTO nuevoTurno(@PathVariable ("id") UUID id, @RequestBody TipoActividadDTO dto){
        Usuario user = usuarioService.findById(id).get();
        return dtoConverter.convertActividadToBasicDTO(service.saveTurno(dto, user));
    }

    @CrossOrigin
    @GetMapping("/ausencia/dia/hoy")
    private List<TipoActividadDTO> todasAusenciasDeUnDia(){
        LocalDate fecha = LocalDate.now();
        return service.findAllByFecha(fecha);
    }

    @CrossOrigin
    @GetMapping("/actividad/{id}")
    private Optional<BasicActividadDTO> unaActividad(@PathVariable ("id") UUID id){
        Optional<BasicActividadDTO> dto = Optional.of(dtoConverter.convertActividadToBasicDTO(service.findById(id).get()));
        return dto;
    }

    @CrossOrigin
    @GetMapping("/turno/dia/hoy/usuario/{id}")
    private List<TipoActividadDTO> elTurnoDeHoy(@PathVariable ("id") UUID id){
        LocalDate fecha = LocalDate.now();
        return service.findTurnoDeUsuario(id, fecha);
    }

    @CrossOrigin
    @GetMapping("ausencia/usuario/{id}")
    private List<BasicActividadDTO> todasAusenciasDeUsuario(@PathVariable ("id") UUID id){
        return service.findAllAusenciasByUsuario(id);
    }

    @CrossOrigin
    @DeleteMapping("/turno/{id}")
    private void borrarTurno(@PathVariable ("id") UUID id){
        service.deleteById(id);
    }
}
