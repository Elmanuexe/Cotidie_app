package salesianos.triana.dam.cotidie.ausencia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.ausencia.model.dto.AusenciaDTO;
import salesianos.triana.dam.cotidie.ausencia.model.dto.AusenciaDTOConverter;
import salesianos.triana.dam.cotidie.ausencia.service.AusenciaService;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;
import salesianos.triana.dam.cotidie.usuario.service.UsuarioService;
import salesianos.triana.dam.cotidie.usuario.service.impl.UsuarioServiceImp;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ausencia")
public class AusenciaController {

    private final AusenciaService service;
    private final AusenciaDTOConverter dtoConverter;
    private final UsuarioServiceImp usuarioService;

    //@PostMapping("/dia/{id}")
    @PostMapping("/dia")
    private AusenciaDTO nuevaAusencia(@AuthenticationPrincipal Usuario usuario, @RequestBody AusenciaDTO dto){
        Usuario user = usuarioService.findById(usuario.getId()).get();
        return dtoConverter.convertAusenciaToDTO(service.save(dto, user));
    }

    @GetMapping("/dia/hoy")
    private List<AusenciaDTO> todasDeUnDia(){
        LocalDate fecha = LocalDate.now();
        return service.findAllByFecha(fecha);
    }

}
