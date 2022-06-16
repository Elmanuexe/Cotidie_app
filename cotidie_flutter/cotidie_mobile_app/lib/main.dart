import 'package:cotidie_mobile_app/pages/home_page.dart';
import 'package:cotidie_mobile_app/pages/register_page.dart';
import 'package:flutter/material.dart';

import 'pages/login_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/login',
      routes: {
        '/login': (context) => const LoginPage(),
        '/register' : (context) => const RegisterPage(),
        '/home' : (context) => const homePage()
      },
    );
  }
}
