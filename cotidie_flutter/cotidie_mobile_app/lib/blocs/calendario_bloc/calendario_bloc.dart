import 'package:bloc/bloc.dart';
import 'package:cotidie_mobile_app/models/planificacion/planificacion_response.dart';
import 'package:cotidie_mobile_app/repository/usuario_repository.dart';
import 'package:equatable/equatable.dart';

import '../../models/evento/eventoDto.dart';
import '../../models/evento/evento_response.dart';

part 'calendario_event.dart';
part 'calendario_state.dart';

class CalendarioBloc extends Bloc<CalendarioEvent, CalendarioState> {
  final UsuarioRepository usuarioRepository;
  CalendarioBloc(this.usuarioRepository) : super(CalendarioInitialState()) {
    on<TryCalendarioEvent>(tryCalendario);
    on<TryEventoEvent>(tryCreate);
  }

  void tryCalendario(
      TryCalendarioEvent event, Emitter<CalendarioState> emitter) async {
    try {
      final calendarioResponse =
          await usuarioRepository.fetchPlanificacion(event.id);
      emitter(CalendarioSuccesState(calendarioResponse));
    } on Exception catch (e) {
      emitter(CalendarioErrorState(e.toString()));
    }
  }

  void tryCreate(TryEventoEvent event, Emitter<CalendarioState> emitter) async {
    try {
      final eventoResponse =
          await usuarioRepository.nuevoEvento(event.eventoDto);
      emitter(EventoSuccesState(eventoResponse));
    } on Exception catch (e) {
      emitter(EventoErrorState(e.toString()));
    }
  }
}
