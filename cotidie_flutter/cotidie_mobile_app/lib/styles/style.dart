import 'package:flutter/material.dart';

class style {


  static Color? bNaranja = const Color.fromRGBO(255, 193, 0, 100);
  static Color? bBotonAzul = const Color(0xff0296f8);
  static Color? grey = const Color(0xff898F9C);
  static Color? lightestGrey = const Color(0xfff0f0f0);

  

  static TextStyle login = TextStyle(
      fontSize: 20, fontWeight: FontWeight.bold, color: Colors.blue[900]);
  
  static TextStyle forgot = TextStyle(
      fontSize: 15, color: Colors.blueAccent[700], fontWeight: FontWeight.w700);

  static TextStyle tazul = TextStyle(
      fontSize: 15, color: Colors.blue[900], fontWeight: FontWeight.w500);
  
  static TextStyle tBlancoG = const TextStyle(
    color: Colors.white, fontSize: 20, fontWeight: FontWeight.w600);
}
