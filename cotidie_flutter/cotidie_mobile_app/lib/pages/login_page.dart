import 'package:cotidie_mobile_app/blocs/login_bloc/login_bloc.dart';
import 'package:cotidie_mobile_app/models/auth/login_dto.dart';
import 'package:cotidie_mobile_app/pages/menu_page.dart';
import 'package:cotidie_mobile_app/repository/auth_repository.dart';
import 'package:cotidie_mobile_app/repository/imp/auth_repository_impl.dart';
import 'package:cotidie_mobile_app/utils/preferences.dart';
import 'package:flutter/material.dart';
import 'package:cotidie_mobile_app/styles/style.dart';

import 'package:flutter_bloc/flutter_bloc.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  late AuthRepository authRepository;
  final _formKey = GlobalKey<FormState>();
  TextEditingController nickController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  void initState() {
    PreferenceUtils.init();
    authRepository = AuthRepositoryImpl();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (context) {
          return LoginBloc(authRepository);
        },
        child: _loginBlocLogic(context));
  }

  Widget _loginBlocLogic(context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;
    return Container(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.height,
      decoration: BoxDecoration(
          gradient: LinearGradient(
        begin: Alignment.centerLeft,
        end: Alignment.centerRight,
        colors: [
          Colors.orange[700]!,
          Colors.amber,
        ],
      )),
      child: Scaffold(
        backgroundColor: Colors.white.withOpacity(0.01),
        resizeToAvoidBottomInset: false,
        body: Column(
          children: [
            Row(
              children: [
                Padding(
                  padding: EdgeInsets.only(
                      left: widthTotal / 10, top: heightTotal / 20),
                  child: Text("Cotidie",
                      style: style.header, textAlign: TextAlign.left),
                ),
              ],
            ),
            Row(
              children: [
                Padding(
                  padding: EdgeInsets.only(left: widthTotal / 10),
                  child: Text("Inicio de sesión",
                      style: style.subtext, textAlign: TextAlign.left),
                ),
              ],
            ),
            BlocConsumer<LoginBloc, LoginState>(
              listenWhen: (context, state) {
                return state is LoginSuccessState || state is LoginErrorState;
              },
              listener: (context, state) {
                if (state is LoginSuccessState) {
                  PreferenceUtils.setString(
                      "jwtToken", state.loginResponse.tokenJwt);
                  PreferenceUtils.setString(
                      "nombre", state.loginResponse.nombre);
                  PreferenceUtils.setString(
                      "apellidos", state.loginResponse.apellidos);
                  PreferenceUtils.setString(
                      "fotoPerfil", state.loginResponse.fotoPerfil);
                  PreferenceUtils.setString("nick", state.loginResponse.nick);
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => const MenuPage()));
                } else if (state is LoginErrorState) {
                  _showSnackbar(context, state.mensaje);
                }
              },
              buildWhen: (context, state) {
                return state is LoginInitialState || state is LoginLoadingState;
              },
              builder: (context, state) {
                if (state is LoginInitialState) {
                  return _loginForm(context);
                } else if (state is LoginLoadingState) {
                  return const Center(child: CircularProgressIndicator());
                } else {
                  return _loginForm(context);
                }
              },
            ),
          ],
        ),
      ),
    );
  }

  void _showSnackbar(BuildContext context, String message) {
    final snackBar = SnackBar(
      content: Text(message),
    );
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
  }

  Widget _loginForm(context) {
    double widthTotal = MediaQuery.of(context).size.width;
    double heightTotal = MediaQuery.of(context).size.height;

    return Form(
        key: _formKey,
        child: Center(
          child: Padding(
            padding: EdgeInsets.only(top: heightTotal/5, left: widthTotal/20, right: widthTotal/20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  margin: const EdgeInsets.only(top: 7, bottom: 8),
                  child: TextFormField(
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
                        labelStyle: const TextStyle(
                            fontSize: 20, color: Colors.black87),
                        hintStyle: const TextStyle(
                            fontSize: 20, color: Colors.blueGrey),
                        filled: true,
                        fillColor: style.lightestGrey),
                  ),
                ),
                Container(
                  margin: const EdgeInsets.only(top: 8, bottom: 20),
                  child: TextFormField(
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
                        labelStyle: const TextStyle(
                            fontSize: 20, color: Colors.black87),
                        filled: true,
                        fillColor: style.lightestGrey),
                    controller: passwordController,
                    obscureText: true,
                  ),
                ),
                SizedBox(
                  child: ElevatedButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        final loginDto = LoginDto(
                            nick: nickController.text,
                            password: passwordController.text);
                        BlocProvider.of<LoginBloc>(context)
                            .add(TryLoginEvent(loginDto));
                      }
                    },
                    child: Text(
                      "Iniciar sesión",
                      style: style.tBlancoG,
                    ),
                    style: ElevatedButton.styleFrom(
                        fixedSize: Size(widthTotal/2, heightTotal/20),
                        shape: const StadiumBorder(),
                        primary: Colors.amber),
                  ),
                ),
                Padding(
                    padding: const EdgeInsets.only(top: 15),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text(
                          "¿No tienes una cuenta? ",
                          style: TextStyle(fontSize: 20),
                        ),
                        InkWell(
                          onTap: () =>
                              {Navigator.pushNamed(context, '/register')},
                          child: Text(
                            'Regístrate ',
                            style: style.forgot,
                          ),
                        )
                      ],
                    ))
              ],
            ),
          ),
        ));
  }
}
