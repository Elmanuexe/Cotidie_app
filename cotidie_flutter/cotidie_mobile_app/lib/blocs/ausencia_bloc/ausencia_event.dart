part of 'ausencia_bloc.dart';

abstract class AusenciaEvent extends Equatable {
  const AusenciaEvent();

  @override
  List<Object> get props => [];
}

class TryAusenciaEvent extends AusenciaEvent{
  final String id;
  const TryAusenciaEvent(this.id);
}