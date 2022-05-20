package salesianos.triana.dam.cotidie.ausencia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.ausencia.model.Ausencia;
import salesianos.triana.dam.cotidie.ausencia.model.dto.AusenciaDTO;
import salesianos.triana.dam.cotidie.ausencia.model.dto.AusenciaDTOConverter;
import salesianos.triana.dam.cotidie.ausencia.repository.AusenciaRepository;
import salesianos.triana.dam.cotidie.shared.service.BaseService;
import salesianos.triana.dam.cotidie.usuario.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AusenciaService extends BaseService<Ausencia, UUID, AusenciaRepository> {

    private final AusenciaRepository repository;
    private final AusenciaDTOConverter dtoConverter;

    public Ausencia save(AusenciaDTO a, Usuario u){
        Ausencia nueva = dtoConverter.convertDTOToAusencia(a);
        nueva.addAusenciaToUser(u);
        return save(nueva);
    }

    public List<AusenciaDTO> findAllByFecha(LocalDate fecha){
        List<Ausencia> ausencias = repository.findAllByFecha(fecha);
        List<AusenciaDTO> ausenciaDTOS =ausencias.stream().map(x -> dtoConverter.convertAusenciaToDTO(x)).collect(Collectors.toList());
        return ausenciaDTOS;
    }


}
