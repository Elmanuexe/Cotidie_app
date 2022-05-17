part of 'login_bloc.dart';

abstract class LoginEvent extends Equatable {
  const LoginEvent();

  @override
  List<Object> get props => [];
}

class TryLoginEvent extends LoginEvent{
  final LoginDto loginDto;
  const TryLoginEvent(this.loginDto);
}
