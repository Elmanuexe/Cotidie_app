import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
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

}
