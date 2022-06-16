part of 'calendario_bloc.dart';

abstract class CalendarioState extends Equatable {
  const CalendarioState();
  
  @override
  List<Object> get props => [];
}

class CalendarioInitialState extends CalendarioState {}
class CalendarioLoadingState extends CalendarioState {}

class CalendarioSuccesState extends CalendarioState{
  final PlanificacionResponse response;
  const CalendarioSuccesState(this.response);
  @override
  List<Object> get props => [response];
}

class CalendarioErrorState extends CalendarioState{
  final String mensaje;
  const CalendarioErrorState(this.mensaje);
  @override
  List<Object> get props => [mensaje];
}

class EventoSuccesState extends CalendarioState{
  final EventoResponse eventoResponse;
  const EventoSuccesState(this.eventoResponse); 
  @override
  List<Object> get props => [eventoResponse];
}

class EventoErrorState extends CalendarioState{
  final String mensaje;
  const EventoErrorState(this.mensaje);
  @override
  List<Object> get props => [mensaje];
}
