import 'dart:convert';

import 'package:cotidie_mobile_app/blocs/ausencia_bloc/ausencia_bloc.dart';
import 'package:cotidie_mobile_app/repository/imp/usuario_repository_impl.dart';
import 'package:cotidie_mobile_app/repository/usuario_repository.dart';
import 'package:cotidie_mobile_app/styles/style.dart';
import 'package:cotidie_mobile_app/utils/preferences.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../models/evento/ausencia_list_response.dart';

class AusenciasPage extends StatefulWidget {
  const AusenciasPage({Key? key}) : super(key: key);

  @override
  State<AusenciasPage> createState() => _AusenciasPageState();
}

class _AusenciasPageState extends State<AusenciasPage> {
  late Future<List<AusenciaListResponse>> ausencias;
  late UsuarioRepository usuarioRepository;

  @override
  void initState() {
    PreferenceUtils.init();
    usuarioRepository = UsuarioRepositoryImpl();
    ausencias = usuarioRepository
        .fetchAusencias(PreferenceUtils.getString("usuario_id")!);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) {
          return AusenciaBloc(usuarioRepository);
        },
        child: _ausenciasPageLogic(context));
  }

  Widget _ausenciasPageLogic(context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    return SizedBox(
      width: widthTotal,
      height: heightTotal,
      child: Scaffold(
        resizeToAvoidBottomInset: true,
        body: SingleChildScrollView(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  FutureBuilder<List<AusenciaListResponse>>(
                      future: (ausencias),
                      builder: (context, snapshot) {
                        if (snapshot.hasData) {
                          return Column(
                            children: [ausenciasUsuario(snapshot.data!)],
                          );
                        } else if (snapshot.hasError) {
                          return Padding(
                            padding: const EdgeInsets.only(top: 100),
                            child: Text('${snapshot.error}'),
                          );
                        }
                        return const CircularProgressIndicator();
                      })
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget ausenciasUsuario(List<AusenciaListResponse>? response) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    if (response != null) {
      return SizedBox(
        height: heightTotal,
        width: widthTotal,
        child: ListView.builder(
            shrinkWrap: true,
            itemCount: response.length,
            itemBuilder: (context, index) {
              return _ausenciaItem(response.elementAt(index));
            }),
      );
    } else {
      return const Center(
        child: Text('No tines publicaciones aún'),
      );
    }
  }

  Widget _ausenciaItem(AusenciaListResponse item) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    return SizedBox(
      child: Center(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.only(top: 50),
              child: SizedBox(
                height: 125,
                width: widthTotal,
                child: Card(
                  color: style.lightestGrey,
                  child: Row(
                    children: [
                      const Icon(
                        Icons.warning_rounded,
                        color: Colors.red,
                        size: 70,
                      ),
                      Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(top:15),
                            child: Text(
                              utf8.decode(item.descripcion.codeUnits),
                              style: style.header_card,
                            ),
                          ),
                          Row(
                            children: [
                              item.todoElDia
                                  ? Text("Todo el día ")
                                  : Container(),
                              Column(
                                children: [
                                  Text("Hora inicio: " + item.horaInicio.toString().substring(10, 19)),
                                  Text("Hora fin: "+item.horaFin.toString().substring(10, 19))
                                ],
                              )
                            ],
                          )
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
