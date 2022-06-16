import 'package:flutter/material.dart';

class style {


  static Color bNaranja = const Color.fromRGBO(255, 193, 0, 100);
  static Color deepBlue = const Color(0xff0296f8);
  static Color grey = const Color(0xff898F9C);
  static Color lightestGrey = Colors.white54;
  static Color bgOrange = Colors.orange[700]!;
  static Color bgYellow = Colors.amber;


  static TextStyle header = TextStyle(
    fontSize: 40, fontWeight: FontWeight.w900, color: Colors.blue[900]
  );
  static TextStyle header_card = const TextStyle(
    fontSize: 30, fontWeight: FontWeight.w900
  );
  static TextStyle subtext = const TextStyle(
    fontSize: 20, fontWeight: FontWeight.w400, color: Colors.white);

  static TextStyle subtextblack = const TextStyle(
    fontSize: 15, fontWeight: FontWeight.w400, color: Colors.black);

  static TextStyle login = TextStyle(
      fontSize: 20, fontWeight: FontWeight.bold, color: Colors.blue[900]);
  
  static TextStyle forgot = TextStyle(
      fontSize: 20, color: Colors.blueAccent[700], fontWeight: FontWeight.w700);

  static TextStyle tazul = TextStyle(
      fontSize: 15, color: Colors.blue[900], fontWeight: FontWeight.w500);
  
  static TextStyle tBlancoG = const TextStyle(
    color: Colors.white, fontSize: 20, fontWeight: FontWeight.w600);
}
