import 'package:bloc/bloc.dart';
import 'package:cotidie_mobile_app/models/auth/login_dto.dart';
import 'package:cotidie_mobile_app/models/auth/login_response.dart';
import 'package:cotidie_mobile_app/repository/auth_repository.dart';
import 'package:equatable/equatable.dart';

part 'login_event.dart';
part 'login_state.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
final AuthRepository authRepository;

  LoginBloc(this.authRepository) : super(LoginInitialState()) {
    on<TryLoginEvent>(tryLogin);
  }

  void tryLogin(TryLoginEvent event, Emitter<LoginState> emitter) async{
    try {
      final loginResponse = await authRepository.doLogin(event.loginDto);
      emitter(LoginSuccessState(loginResponse));
    } on Exception catch (e) {
      emitter(LoginErrorState(e.toString()));
    }
  }
}
