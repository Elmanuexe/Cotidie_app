import 'dart:convert';

import 'package:cotidie_mobile_app/models/auth/register_dto.dart';
import 'package:cotidie_mobile_app/models/auth/login_response.dart';
import 'package:cotidie_mobile_app/models/auth/login_dto.dart';
import 'package:cotidie_mobile_app/repository/auth_repository.dart';
import 'package:cotidie_mobile_app/utils/const.dart';
import 'package:http/http.dart' as http;
import 'package:image_picker/image_picker.dart';
import 'package:http_parser/http_parser.dart';

class AuthRepositoryImpl extends AuthRepository {
  @override
  Future<LoginResponse> doLogin(LoginDto loginDto) async{
    Map<String, String> headers = {
      'Content-Type': 'application/json',
    };
    final response = await http.post(Uri.parse('$baseUrl/auth/login'),
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode(loginDto.toJson()));
    if (response.statusCode == 200) {
      return LoginResponse.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Credenciales incorrectos');
    }
  }

  @override
  Future<LoginResponse> doRegister(RegisterDto dto, XFile file) async {
    var request = http.MultipartRequest('POST', Uri.parse('$baseUrl/auth/register/user'));
    request.headers.addAll({'Content-Type': 'multipart/form-data'});
    request.files.add(await http.MultipartFile.fromPath('file', file.path));
    request.files.add(http.MultipartFile.fromString('usuario', jsonEncode(dto.toJson()), contentType: MediaType('application', 'json')));
    var res = await request.send();
    var body = await res.stream.bytesToString();
    if (res.statusCode==201) {
      return LoginResponse.fromJson(jsonDecode(body));
    }else{
      throw Exception('Error en el registro');
    }
  }

  @override
  Future<LoginResponse> getUsuarioLogged(String token) async {
    var response = await http.get(Uri.parse('$baseUrl/me'),
        headers: {'Authorization': 'Bearer $token'});
    if (response.statusCode == 200) {
      return LoginResponse.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Error en la petición: $baseUrl/me');
    }
  }
  
}