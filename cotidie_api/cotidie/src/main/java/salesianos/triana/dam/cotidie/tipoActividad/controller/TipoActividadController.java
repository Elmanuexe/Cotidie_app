package salesianos.triana.dam.cotidie.tipoActividad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTO;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTOConverter;
import salesianos.triana.dam.cotidie.tipoActividad.service.TipoActividadService;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.impl.UsuarioServiceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TipoActividadController {

    private final TipoActividadDTOConverter dtoConverter;
    private final TipoActividadService service;
    private final UsuarioServiceImp usuarioService;

    //@PostMapping("/dia/{id}")
    @PostMapping("/ausencia/dia")
    private TipoActividadDTO nuevaAusencia(@AuthenticationPrincipal Usuario usuario, @RequestBody TipoActividadDTO dto){
        Usuario user = usuarioService.findById(usuario.getId()).get();
        return dtoConverter.convertAusenciaToDTO(service.saveAusencia(dto, user));
    }

    @CrossOrigin
    @GetMapping("/ausencia/dia/hoy")
    private List<TipoActividadDTO> todasAusenciasDeUnDia(){
        LocalDate fecha = LocalDate.now();
        return service.findAllByFecha(fecha);
    }

    @GetMapping("ausencia/usuario/{id}")
    private List<TipoActividadDTO> todasAusenciasDeUsuario(@PathVariable ("id") UUID id){
        return service.findAllByUsuario(id);
    }
}
