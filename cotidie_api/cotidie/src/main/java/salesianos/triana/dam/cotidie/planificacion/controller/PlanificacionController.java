package salesianos.triana.dam.cotidie.planificacion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import salesianos.triana.dam.cotidie.planificacion.model.PlanificacionMensual;
import salesianos.triana.dam.cotidie.planificacion.model.dto.PlanDTO;
import salesianos.triana.dam.cotidie.planificacion.service.PlanificacionService;
import salesianos.triana.dam.cotidie.tipoActividad.model.dto.TipoActividadDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanificacionController {

    private final PlanificacionService service;

    @CrossOrigin
    @GetMapping("/esteMes/usuario/{id}")
    private PlanDTO todasLasActDeUnMesPorUsuario(@PathVariable UUID id){
        LocalDate fecha = LocalDate.now();
        return service.findDTOPorMes(fecha, id);
    }


}
