import 'package:cotidie_mobile_app/models/auth/login_dto.dart';
import 'package:cotidie_mobile_app/models/auth/login_response.dart';
import 'package:cotidie_mobile_app/models/auth/register_dto.dart';
import 'package:image_picker/image_picker.dart';

abstract class AuthRepository {
  Future <LoginResponse> doLogin (LoginDto loginDto);
  Future <LoginResponse> doRegister (RegisterDto dto,XFile file);
  Future <LoginResponse> getUsuarioLogged (String token);
}