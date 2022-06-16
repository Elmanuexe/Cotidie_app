import 'dart:convert';

import 'package:cotidie_mobile_app/blocs/calendario_bloc/calendario_bloc.dart';
import 'package:cotidie_mobile_app/models/evento/eventoDto.dart';
import 'package:cotidie_mobile_app/models/planificacion/planificacion_response.dart';
import 'package:cotidie_mobile_app/repository/usuario_repository.dart';
import 'package:cotidie_mobile_app/styles/style.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/key.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../repository/imp/usuario_repository_impl.dart';
import '../utils/preferences.dart';

class Calendario extends StatefulWidget {
  const Calendario({Key? key}) : super(key: key);

  @override
  State<Calendario> createState() => _CalendarioState();
}

class _CalendarioState extends State<Calendario> {
  late Future<PlanificacionResponse> planificacion;
  late UsuarioRepository usuarioRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController fechaController = TextEditingController();
  TextEditingController descripcionController = TextEditingController();
  TextEditingController horaInicioController = TextEditingController();
  TextEditingController horaFinController = TextEditingController();
  bool todoElDia = false;

  @override
  void initState() {
    PreferenceUtils.init();
    usuarioRepository = UsuarioRepositoryImpl();
    planificacion = usuarioRepository
        .fetchPlanificacion(PreferenceUtils.getString("usuario_id")!);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) {
          return CalendarioBloc(usuarioRepository);
        },
        child: _calendarioPageLogic(context));
  }

  Widget _calendarioPageLogic(context) {
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
                Padding(
                  padding: const EdgeInsets.only(top: 50),
                  child: Row(
                    children: [
                      ElevatedButton(
                        onPressed: () => showDialog(
                            context: context,
                            builder: (BuildContext context) => AlertDialog(
                                  title: Text(
                                    "Nueva ausencia",
                                    style: style.header_card,
                                  ),
                                  content: _actividadForm(context),
                                )),
                        child: Text("Nueva ausencia"),
                      ),
                    ],
                  ),
                ),
                FutureBuilder<PlanificacionResponse>(
                    future: (planificacion),
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return Column(
                          children: [planificacionUsuario(snapshot.data, context)],
                        );
                      } else if (snapshot.hasError) {
                        return Padding(
                          padding: const EdgeInsets.only(top: 25),
                          child: Text('${snapshot.error}'),
                        );
                      }
                      return const CircularProgressIndicator();
                    })
              ],
            )
          ],
        )),
      ),
    );
  }

  Widget planificacionUsuario(PlanificacionResponse? response, context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    if (response != null) {
      return SizedBox(
        height: heightTotal,
        width: widthTotal,
        child: ListView.builder(
            shrinkWrap: true,
            itemCount: response.eventos.length,
            itemBuilder: (context, index) {
              return _actividadItem(response.eventos.elementAt(index), context);
            }),
      );
    } else {
      return const Center(
        child: Text('No tines panificación aún'),
      );
    }
  }

  Widget _actividadItem(Evento item, context) {
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
                      item.tipo == "AUSENCIA"
                          ? const Icon(
                              Icons.warning_rounded,
                              color: Colors.red,
                              size: 70,
                            )
                          : Container(),
                      item.tipo == "TRABAJO"
                          ? Icon(
                              Icons.work_history,
                              color: selectEventColor(item),
                              size: 70,
                            )
                          : Container(),
                      Column(
                        children: [
                          Row(
                            children: [
                              Padding(
                                padding: const EdgeInsets.only(top: 15),
                                child: Text(
                                  item.fecha.toString().substring(0, 10),
                                  style: style.header_card,
                                ),
                              ),
                              item.todoElDia
                                  ? Padding(
                                      padding: EdgeInsets.only(top: 15),
                                      child: Text(
                                        " Todo el día",
                                        style: style.subtextblack,
                                      ),
                                    )
                                  : Container(),
                            ],
                          ),
                          Row(
                            children: [
                              Column(
                                children: [
                                  Text(utf8.decode(item.descripcion.codeUnits)),
                                  Text("Hora inicio: " +
                                      item.horaInicio
                                          .toString()
                                          .substring(10, 19)),
                                  Text("Hora fin: " +
                                      item.horaFin.toString().substring(10, 19))
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

  Color selectEventColor(Evento item) {
    if (utf8.decode(item.descripcion.codeUnits) == "Turno de mañana") {
      return Colors.orange;
    } else if (item.descripcion == "Turno de tarde") {
      return Colors.lightBlue;
    } else {
      return Colors.blue[900]!;
    }
  }

  Widget _actividadForm(context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    return Form(
      key: _formKey,
      child: Center(
          child: Padding(
        padding: EdgeInsets.all(8),
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                margin: const EdgeInsets.only(top: 7, bottom: 8),
                child: TextFormField(
                  controller: fechaController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.grey, width: 2)),
                      labelText: 'Fecha',
                      hintText: 'YYYY-MM-DD',
                      labelStyle:
                          const TextStyle(fontSize: 20, color: Colors.black87),
                      hintStyle:
                          const TextStyle(fontSize: 20, color: Colors.blueGrey),
                      filled: true,
                      fillColor: style.lightestGrey),
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 7, bottom: 8),
                child: TextFormField(
                  controller: descripcionController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.grey, width: 2)),
                      labelText: 'Detalle',
                      hintText: 'Turno de x para los turnos',
                      labelStyle:
                          const TextStyle(fontSize: 20, color: Colors.black87),
                      hintStyle:
                          const TextStyle(fontSize: 20, color: Colors.blueGrey),
                      filled: true,
                      fillColor: style.lightestGrey),
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 7, bottom: 8),
                child: TextFormField(
                  controller: horaInicioController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.grey, width: 2)),
                      labelText: 'Hora de inicio',
                      hintText: '00:00',
                      labelStyle:
                          const TextStyle(fontSize: 20, color: Colors.black87),
                      hintStyle:
                          const TextStyle(fontSize: 20, color: Colors.blueGrey),
                      filled: true,
                      fillColor: style.lightestGrey),
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 7, bottom: 8),
                child: TextFormField(
                  controller: horaFinController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide:
                              const BorderSide(color: Colors.grey, width: 2)),
                      labelText: 'Hora de inicio',
                      hintText: '00:00',
                      labelStyle:
                          const TextStyle(fontSize: 20, color: Colors.black87),
                      hintStyle:
                          const TextStyle(fontSize: 20, color: Colors.blueGrey),
                      filled: true,
                      fillColor: style.lightestGrey),
                ),
              ),
              Row(
                children: [
                  Text("Todo el día: "),
                  Checkbox(
                    value: todoElDia,
                    onChanged: (bool? value) {
                      setState(() {
                        todoElDia = value!;
                      });
                    },
                  ),
                ],
              ),
              SizedBox(
                child: ElevatedButton(
                  onPressed: () {
                    if (_formKey.currentState!.validate()) {
                      final eventoDto = EventoDto(
                          fecha: DateTime.parse(fechaController.text),
                          descripcion: descripcionController.text,
                          horaInicio: DateTime.parse((fechaController.text+" "+horaInicioController.text+":01Z")),
                          horaFin: DateTime.parse((fechaController.text+" "+horaFinController.text+":01Z")),
                          todoElDia: todoElDia);
                      BlocProvider.of<CalendarioBloc>(context)
                          .add(TryEventoEvent(eventoDto));
                    }
                  },
                  child: Text(
                    "Crear",
                    style: style.tBlancoG,
                  ),
                  style: ElevatedButton.styleFrom(
                      fixedSize: Size(widthTotal / 2, heightTotal / 20),
                      shape: const StadiumBorder(),
                      primary: Colors.amber),
                ),
              ),
            ],
          ),
        ),
      )),
    );
  }
}
