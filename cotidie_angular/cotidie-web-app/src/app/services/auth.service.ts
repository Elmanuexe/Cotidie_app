import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { LoginDTO, LoginResponse } from '../models/login';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'aplication/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient){ }

  login(dto: LoginDTO): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.apiBaseUrl}/auth/login`, dto);

  }



}
