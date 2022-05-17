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
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: BlocConsumer<LoginBloc, LoginState>(
        listenWhen: (context, state) {
          return state is LoginSuccessState || state is LoginErrorState;
        },
        listener: (context, state) {
          if (state is LoginSuccessState) {
            PreferenceUtils.setString("jwtToken", state.loginResponse.tokenJwt);
            PreferenceUtils.setString("nombre", state.loginResponse.nombre);
            PreferenceUtils.setString(
                "apellidos", state.loginResponse.apellidos);
            PreferenceUtils.setString(
                "fotoPerfil", state.loginResponse.fotoPerfil);
            PreferenceUtils.setString("nick", state.loginResponse.nick);
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const MenuPage()));
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
            padding: const EdgeInsets.all(40.0),
            child: Column(
              children: [
                TextFormField(
                  controller: nickController,
                  decoration:
                      const InputDecoration(hintText: "Nombre de usuario"),
                ),
                Container(
                  margin: const EdgeInsets.only(top: 7, bottom: 20),
                  child: TextFormField(
                    decoration: const InputDecoration(hintText: "Contraseña"),
                    controller: passwordController,
                    obscureText: true,
                  ),
                ),
                SizedBox(
                  width: widthTotal,
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
                    child: const Text("Iniciar sesión"),
                    style: ElevatedButton.styleFrom(primary: Colors.blue[700]),
                  ),
                ),
                Padding(
                    padding: const EdgeInsets.all(15),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text(
                          "Don't have an account?",
                          style: TextStyle(color: Colors.grey, fontSize: 12),
                        ),
                        Container(
                          margin: const EdgeInsets.only(left: 10),
                          child: InkWell(
                            onTap: () =>
                                {Navigator.pushNamed(context, '/register')},
                            child: const Text(
                              'Sign up',
                              style: TextStyle(
                                  fontSize: 14,
                                  fontWeight: FontWeight.w500,
                                  color: Colors.blue),
                            ),
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
