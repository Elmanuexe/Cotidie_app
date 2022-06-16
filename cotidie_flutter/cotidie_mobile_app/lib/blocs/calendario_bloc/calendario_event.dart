part of 'calendario_bloc.dart';

abstract class CalendarioEvent extends Equatable {
  const CalendarioEvent();

  @override
  List<Object> get props => [];
}

class TryCalendarioEvent extends CalendarioEvent{
  final String id;
  const TryCalendarioEvent(this.id);
}

class TryEventoEvent extends CalendarioEvent{
  final EventoDto eventoDto;
  const TryEventoEvent(this.eventoDto);
}