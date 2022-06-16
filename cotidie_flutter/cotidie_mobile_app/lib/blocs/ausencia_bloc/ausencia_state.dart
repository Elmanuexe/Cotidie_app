part of 'ausencia_bloc.dart';

abstract class AusenciaState extends Equatable {
  const AusenciaState();
  
  @override
  List<Object> get props => [];
}

class AusenciaInitialState extends AusenciaState {}
class AusenciaLoadingState extends AusenciaState {}

class AusenciaSuccesState extends AusenciaState{
  final List<AusenciaListResponse> response;
  const AusenciaSuccesState(this.response);
  @override
  List<Object> get props => [response];
}

class AusenciaErrorState extends AusenciaState{
  final String mensaje;
  const AusenciaErrorState(this.mensaje);
  @override
  List<Object> get props => [mensaje];
}
