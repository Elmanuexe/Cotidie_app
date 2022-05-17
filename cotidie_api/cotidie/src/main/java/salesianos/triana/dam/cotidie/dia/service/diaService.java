package salesianos.triana.dam.cotidie.dia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.cotidie.dia.model.Dia;
import salesianos.triana.dam.cotidie.dia.repository.DiaRepository;
import salesianos.triana.dam.cotidie.shared.service.BaseService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class diaService extends BaseService<Dia, UUID, DiaRepository> {

}
