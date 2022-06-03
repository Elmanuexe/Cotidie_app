import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { AusenciaList } from '../models/ausenciaListResponse';




@Injectable({
  providedIn: 'root'
})
export class AusenciaService {

  constructor(private http: HttpClient){ }

  fetchAusenciasHoy(): Observable<AusenciaList> {

      const headers= ({
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
        'Access-Control-Allow-Origin': '*'
      });
    
    return this.http.get<AusenciaList>(`${environment.apiBaseUrl}/ausencia/dia/hoy`, {headers: headers});

  }
}
