import 'dart:convert';
import 'dart:io';

import 'package:cotidie_mobile_app/models/evento/evento_response.dart';
import 'package:cotidie_mobile_app/models/evento/eventoDto.dart';
import 'package:cotidie_mobile_app/models/planificacion/planificacion_response.dart';
import 'package:cotidie_mobile_app/repository/usuario_repository.dart';
import 'package:cotidie_mobile_app/utils/const.dart';
import 'package:cotidie_mobile_app/utils/preferences.dart';
import 'package:http/http.dart' as http;

import '../../models/evento/ausencia_list_response.dart';

class UsuarioRepositoryImpl extends UsuarioRepository {
  @override
  Future<List<AusenciaListResponse>> fetchAusencias(String id) async {
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      HttpHeaders.authorizationHeader:
          'Bearer ' + PreferenceUtils.getString("jwtToken")!,
    };
    final response = await http.get(
      Uri.parse('$baseUrl/ausencia/usuario/' + id),
      headers: headers,
    );
    if (response.statusCode == 200) {
      return List.from(jsonDecode(response.body))
          .map((e) => AusenciaListResponse.fromJson(e))
          .toList();
    } else {
      throw Exception(response.body);
    }
  }

  @override
  Future<PlanificacionResponse> fetchPlanificacion(String id) async {
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      HttpHeaders.authorizationHeader:
          'Bearer ' + PreferenceUtils.getString("jwtToken")!,
    };
    final response = await http.get(
      Uri.parse('$baseUrl/plan/esteMes/usuario/' + id),
      headers: headers,
    );
    if (response.statusCode == 200) {
      return PlanificacionResponse.fromJson(jsonDecode(response.body));
    } else {
      throw Exception(response.body);
    }
  }

  @override
  Future<EventoResponse> nuevoEvento(EventoDto dto) async {
    Map<String, String> headers = {
      'Content-Type': 'application/json',
      HttpHeaders.authorizationHeader:
          'Bearer ' + PreferenceUtils.getString("jwtToken")!,
    };
    final response = await http.post(Uri.parse('$baseUrl/ausencia/dia'),
        headers: headers, body: jsonEncode(dto.toJson()));
    if (response.statusCode == 200) {

      return EventoResponse.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Datos incorrectos');
    }
  }
}
