class EventoResponse {
    EventoResponse({
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

    factory EventoResponse.fromJson(Map<String, dynamic> json) => EventoResponse(
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
