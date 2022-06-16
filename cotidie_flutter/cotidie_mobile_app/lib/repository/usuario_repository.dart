import 'package:cotidie_mobile_app/models/evento/eventoDto.dart';
import 'package:cotidie_mobile_app/models/evento/evento_response.dart';
import 'package:cotidie_mobile_app/models/planificacion/planificacion_response.dart';

import '../models/evento/ausencia_list_response.dart';

abstract class UsuarioRepository {

  Future<List<AusenciaListResponse>> fetchAusencias(String id);
  Future<PlanificacionResponse> fetchPlanificacion(String id);
  Future<EventoResponse> nuevoEvento(EventoDto dto);
}