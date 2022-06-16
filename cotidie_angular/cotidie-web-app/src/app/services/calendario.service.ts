import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Listausuarios } from '../models/listUsersResponse';
import { Actividad, Planificacion } from '../models/planificacionResponse';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'aplication/json',
    'Authorization': `Bearer ${localStorage.getItem('token')}`,
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CalendarioService {

  constructor(private http: HttpClient) { }

  fetchEventos(id:string): Observable<Planificacion> {
    return this.http.get<Planificacion>(`${environment.apiBaseUrl}/plan/esteMes/usuario/`+id, DEFAULT_HEADERS)
  }

  getEvento(id:string): Observable<Actividad> {
    return this.http.get<Actividad>(`${environment.apiBaseUrl}/actividad/`+id, DEFAULT_HEADERS)
  }

  crearActividad(id: string, actividad: Actividad): Observable<Actividad> {
    const headers = {
      'Content-Type': 'aplication/json',
      Authorization: `Bearer ${localStorage.getItem('token')}`,
      'Access-Control-Allow-Origin': '*',
    };
    return this.http.post<Actividad>(`${environment.apiBaseUrl}/turno/dia/`+id,actividad,{headers});
  }

  borrarActividad(id:string): Observable<any>{
    return this.http.delete(`${environment.apiBaseUrl}/turno/` + id, DEFAULT_HEADERS);
  }

}
