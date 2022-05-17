import 'package:bloc/bloc.dart';
import 'package:cotidie_mobile_app/models/auth/login_response.dart';
import 'package:cotidie_mobile_app/models/auth/register_dto.dart';
import 'package:cotidie_mobile_app/repository/auth_repository.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter/widgets.dart';
import 'package:image_picker/image_picker.dart';

part 'register_event.dart';
part 'register_state.dart';

class RegisterBloc extends Bloc<RegisterEvent, RegisterState> {
  final AuthRepository authRepository;
  
  RegisterBloc(this.authRepository) : super(RegisterInitialState()) {
    on<DoRegisterEvent>(tryRegister);
    on<SelectImageEvent>(pickImage);
  }

  void tryRegister(DoRegisterEvent event, Emitter<RegisterState> emitter) async {
    final registerResponse = await authRepository.doRegister(event.dto, event.resource);
    try {
      emitter(RegisterSuccessState(registerResponse));
    } on Exception catch (e) {
      emitter(RegisterErrorState(e.toString()));
    }
  }

  void pickImage(SelectImageEvent event, Emitter<RegisterState> emitter) async {
    final ImagePicker _picker = ImagePicker();
    try {
      final XFile? resource = await _picker.pickImage(source: event.resource);
      if (resource != null) {
        emitter(RegisterImageSuccessState(resource));
      } else {
        emitter(const RegisterImageErrorState('Se debe de proporcionar una imagen'));
      }
    } catch (e) {
      emitter(RegisterErrorState(e.toString()));
    }
  }

}
