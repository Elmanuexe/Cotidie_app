class PlanificacionResponse {
    PlanificacionResponse({
        required this.fechaInicio,
        required this.fechaFin,
        required this.eventos,
        required this.usuario,
    });

    DateTime fechaInicio;
    dynamic fechaFin;
    List<Evento> eventos;
    Usuario usuario;

    factory PlanificacionResponse.fromJson(Map<String, dynamic> json) => PlanificacionResponse(
        fechaInicio: DateTime.parse(json["fechaInicio"]),
        fechaFin: json["fechaFin"],
        eventos: List<Evento>.from(json["eventos"].map((x) => Evento.fromJson(x))),
        usuario: Usuario.fromJson(json["usuario"]),
    );

    Map<String, dynamic> toJson() => {
        "fechaInicio": "${fechaInicio.year.toString().padLeft(4, '0')}-${fechaInicio.month.toString().padLeft(2, '0')}-${fechaInicio.day.toString().padLeft(2, '0')}",
        "fechaFin": fechaFin,
        "eventos": List<dynamic>.from(eventos.map((x) => x.toJson())),
        "usuario": usuario.toJson(),
    };
}

class Evento {
    Evento({
        required this.id,
        required this.tipo,
        required this.fecha,
        required this.descripcion,
        required this.horaInicio,
        required this.horaFin,
        required this.todoElDia,
    });

    String id;
    String tipo;
    DateTime fecha;
    String descripcion;
    DateTime horaInicio;
    DateTime horaFin;
    bool todoElDia;

    factory Evento.fromJson(Map<String, dynamic> json) => Evento(
        id: json["id"],
        tipo: json["tipo"],
        fecha: DateTime.parse(json["fecha"]),
        descripcion: json["descripcion"],
        horaInicio: DateTime.parse(json["horaInicio"]),
        horaFin: DateTime.parse(json["horaFin"]),
        todoElDia: json["todoElDia"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "tipo": tipo,
        "fecha": "${fecha.year.toString().padLeft(4, '0')}-${fecha.month.toString().padLeft(2, '0')}-${fecha.day.toString().padLeft(2, '0')}",
        "descripcion": descripcion,
        "horaInicio": horaInicio.toIso8601String(),
        "horaFin": horaFin.toIso8601String(),
        "todoElDia": todoElDia,
    };
}

class Usuario {
    Usuario({
        required this.nombre,
        required this.apellidos,
        required this.fotoPerfil,
        required this.id,
    });

    String nombre;
    String apellidos;
    String fotoPerfil;
    String id;

    factory Usuario.fromJson(Map<String, dynamic> json) => Usuario(
        nombre: json["nombre"],
        apellidos: json["apellidos"],
        fotoPerfil: json["fotoPerfil"],
        id: json["id"],
    );

    Map<String, dynamic> toJson() => {
        "nombre": nombre,
        "apellidos": apellidos,
        "fotoPerfil": fotoPerfil,
        "id": id,
    };
}
