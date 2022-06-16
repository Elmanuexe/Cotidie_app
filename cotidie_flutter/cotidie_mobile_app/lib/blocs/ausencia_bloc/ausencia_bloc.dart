import 'package:bloc/bloc.dart';
import 'package:cotidie_mobile_app/repository/usuario_repository.dart';
import 'package:equatable/equatable.dart';

import '../../models/evento/ausencia_list_response.dart';

part 'ausencia_event.dart';
part 'ausencia_state.dart';

class AusenciaBloc extends Bloc<AusenciaEvent, AusenciaState> {
  final UsuarioRepository usuarioRepository;
  AusenciaBloc(this.usuarioRepository) : super(AusenciaInitialState()) {
    on<TryAusenciaEvent>(tryAusenciaList);
  }

  void tryAusenciaList(TryAusenciaEvent event, Emitter<AusenciaState> emitter)async{
    try {
      final ausenciaResponse = await usuarioRepository.fetchAusencias(event.id);
      emitter(AusenciaSuccesState(ausenciaResponse));
    } on Exception catch (e) {
      emitter(AusenciaErrorState(e.toString()));
    }
  }
}
