import 'package:cotidie_mobile_app/blocs/login_bloc/login_bloc.dart';
import 'package:cotidie_mobile_app/blocs/register_bloc/register_bloc.dart';
import 'package:cotidie_mobile_app/models/auth/register_dto.dart';
import 'package:cotidie_mobile_app/pages/menu_page.dart';
import 'package:cotidie_mobile_app/repository/auth_repository.dart';
import 'package:cotidie_mobile_app/repository/imp/auth_repository_impl.dart';
import 'package:cotidie_mobile_app/styles/style.dart';
import 'package:cotidie_mobile_app/utils/preferences.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_picker/image_picker.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({Key? key}) : super(key: key);

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  XFile? file;
  ImagePicker picker = ImagePicker();
  final _formKey = GlobalKey<FormState>();
  late final AuthRepository authRepository;
  TextEditingController nickController = TextEditingController();
  TextEditingController nombreController = TextEditingController();
  TextEditingController apellidosController = TextEditingController();
  TextEditingController contrasenaController = TextEditingController();
  TextEditingController contrasena2Controller = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController telefonoController = TextEditingController();

  @override
  void initState() {
    authRepository = AuthRepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.centerRight,
            end: Alignment.centerLeft,
            colors: <Color>[Colors.amber, Colors.amberAccent],
            tileMode: TileMode.mirror,
          ),
        ),
        child: BlocProvider(
          create: (context) {
            return RegisterBloc(authRepository);
          },
          child: _registerBlocLogic(),
        ));
  }

  _registerBlocLogic() {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: BlocConsumer<RegisterBloc, RegisterState>(
        listenWhen: (context, state) {
          return state is RegisterSuccessState ||
              state is RegisterErrorState ||
              state is RegisterImageErrorState ||
              state is RegisterDateErrorState;
        },
        listener: (context, state) {
          if (state is RegisterSuccessState) {
            PreferenceUtils.setString("jwtToken", state.loginResponse.tokenJwt);
            PreferenceUtils.setString("nombre", state.loginResponse.nombre);
            PreferenceUtils.setString(
                "apellidos", state.loginResponse.apellidos);
            PreferenceUtils.setString(
                "fotoPerfil", state.loginResponse.fotoPerfil);
            PreferenceUtils.setString("nick", state.loginResponse.nick);
            Navigator.push(
                context, MaterialPageRoute(builder: (context) => MenuPage()));
          } else if (state is RegisterErrorState) {
            _errorMessage(context, state.message);
          } else if (state is RegisterImageErrorState) {
            _errorMessage(context, state.message);
          }
        },
        buildWhen: (context, state) {
          return state is RegisterImageSuccessState ||
              state is LoginInitialState ||
              state is LoginLoadingState;
        },
        builder: (context, state) {
          if (state is LoginLoadingState) {
            return const CircularProgressIndicator();
          } else if (state is RegisterImageSuccessState) {
            file = state.file;
            return _registerForm(context);
          } else {
            return _registerForm(context);
          }
        },
      ),
    );
  }

  _registerForm(BuildContext context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;

    return Form(
        child: Center(
      child: Padding(
        padding: const EdgeInsets.all(40.0),
        child: Column(
          children: [
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                width: widthTotal,
                child: TextField(
                  controller: nickController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Nombre de usuario',
                      hintText: 'Escriba su nombre de usuario',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: nombreController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Nombre',
                      hintText: 'Escriba su nombre sin apellidos',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: apellidosController,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Apellidos',
                      hintText: 'Escriba sus apellidos',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: emailController,
                  obscureText: true,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Email',
                      hintText: 'Escriba su dirección de email',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: telefonoController,
                  obscureText: true,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Teléfono',
                      hintText: 'Escriba su número de teléfono',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: contrasenaController,
                  obscureText: true,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Contraseña',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
                margin: const EdgeInsets.only(top: 20),
                height: 50,
                child: TextField(
                  controller: contrasena2Controller,
                  obscureText: true,
                  decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                          borderSide: const BorderSide(
                              color: Colors.transparent, width: 0)),
                      labelText: 'Confirmar contraseña',
                      labelStyle: TextStyle(fontSize: 17, color: style.grey),
                      filled: true,
                      fillColor: style.lightestGrey),
                )),
            Container(
              margin: const EdgeInsets.fromLTRB(50, 20, 50, 0),
              child: ElevatedButton(
                onPressed: () => {
                  BlocProvider.of<RegisterBloc>(context)
                      .add(const SelectImageEvent(ImageSource.gallery))
                },
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                        margin: const EdgeInsets.only(left: 5),
                        child: Text(
                          'Elija una foto de perfil',
                          style: style.tBlancoG,
                        ))
                  ],
                ),
                style: ElevatedButton.styleFrom(primary: Colors.amber),
              ),
            ),
            Container(
                margin: const EdgeInsets.only(top: 30),
                width: widthTotal - widthTotal / 2,
                child: ElevatedButton(
                  onPressed: () {
                    if (file != null) {
                      final registerDto = RegisterDto(
                        nick: nickController.text,
                        nombre: nombreController.text,
                        apellidos: apellidosController.text,
                        email: emailController.text,
                        telefono: telefonoController.text,
                        password: contrasenaController.text,
                        password2: contrasena2Controller.text,
                      );
                      BlocProvider.of<RegisterBloc>(context)
                          .add(DoRegisterEvent(file!, registerDto));
                    } else {
                      _errorMessage(
                          context, 'Se debe de elegir una imagen de perfil');
                    }
                  },
                  child: const Text('Registrarse'),
                  style: ElevatedButton.styleFrom(primary: Colors.amber),
                )),
          ],
        ),
      ),
    ));
  }

  void _errorMessage(BuildContext context, message) {
    final snackBar = SnackBar(
      content: Text(message),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }
}
