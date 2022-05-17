class RegisterDto {
  RegisterDto({
    required this.nick,
    required this.email,
    required this.nombre,
    required this.apellidos,
    required this.password,
    required this.password2,
    required this.telefono,
  });
  late final String nick;
  late final String email;
  late final String nombre;
  late final String apellidos;
  late final String password;
  late final String password2;
  late final String telefono;
  
  RegisterDto.fromJson(Map<String, dynamic> json){
    nick = json['nick'];
    email = json['email'];
    nombre = json['nombre'];
    apellidos = json['apellidos'];
    password = json['password'];
    password2 = json['password2'];
    telefono = json['telefono'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['nick'] = nick;
    _data['email'] = email;
    _data['nombre'] = nombre;
    _data['apellidos'] = apellidos;
    _data['password'] = password;
    _data['password2'] = password2;
    _data['telefono'] = telefono;
    return _data;
  }
}