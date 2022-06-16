class EventoDto {
    EventoDto({
        required this.fecha,
        required this.descripcion,
        required this.horaInicio,
        required this.horaFin,
        required this.todoElDia,
    });

    DateTime fecha;
    String descripcion;
    DateTime horaInicio;
    DateTime horaFin;
    bool todoElDia;

    factory EventoDto.fromJson(Map<String, dynamic> json) => EventoDto(
        fecha: DateTime.parse(json["fecha"]),
        descripcion: json["descripcion"],
        horaInicio: DateTime.parse(json["horaInicio"]),
        horaFin: DateTime.parse(json["horaFin"]),
        todoElDia: json["todoElDia"],
    );

    Map<String, dynamic> toJson() => {
        "fecha": "${fecha.year.toString().padLeft(4, '0')}-${fecha.month.toString().padLeft(2, '0')}-${fecha.day.toString().padLeft(2, '0')}",
        "descripcion": descripcion,
        "horaInicio": horaInicio.toIso8601String(),
        "horaFin": horaFin.toIso8601String(),
        "todoElDia": todoElDia,
    };
}
