import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Actividad } from '../models/ausenciaListResponse';
import { Listausuarios } from '../models/listUsersResponse';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'aplication/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient){ }

  listarUsuarios(): Observable<Listausuarios> {
    return this.http.get<Listausuarios>(`${environment.apiBaseUrl}/usuario/todos`)
  }

  fetchTurno(id: string): Observable<Actividad>{
    return this.http.get<Actividad>(`${environment.apiBaseUrl}/turno/dia/hoy/usuario/`+id)
  }

}
