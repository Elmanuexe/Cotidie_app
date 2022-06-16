import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AusenciaList } from 'src/app/models/ausenciaListResponse';
import { AusenciaService } from 'src/app/services/ausencia.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-ausencias',
  templateUrl: './ausencias.component.html',
  styleUrls: ['./ausencias.component.css'],
})
export class AusenciasComponent implements OnInit {
  ausencias?: AusenciaList;

  constructor(private service: AusenciaService) {}

  ngOnInit(): void {
    this.service.fetchAusenciasHoy().subscribe((res) => {
      res.forEach((x) => {
        if (x.todoElDia) {
          x.horaFin = "00:00"
        } else{
          x.horaFin = x.horaFin.slice(11, 16);
        }
      });
      res.forEach((x) => {
        if (x.todoElDia) {
          x.horaInicio = "00:00"
        } else{
          x.horaInicio = x.horaInicio.slice(11, 16);
        }
      });

      this.ausencias = res;
    });
  }
}
