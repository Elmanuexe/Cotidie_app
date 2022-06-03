import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AusenciaList } from 'src/app/models/ausenciaListResponse';
import { AusenciaService } from 'src/app/services/ausencia.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-ausencias',
  templateUrl: './ausencias.component.html',
  styleUrls: ['./ausencias.component.css']
})
export class AusenciasComponent implements OnInit {

  ausencias?: AusenciaList;


  constructor(
    private service: AusenciaService
  ) { }

  ngOnInit(): void {
    this.service.fetchAusenciasHoy().subscribe(res => {
      let horaFin= res.map(x => {x.horaFin.trim(11)})
      let horaInicio= res.map(x => {x.horaInicio.trim(10)})
      this.ausencias = res
      this.ausencias.map(x => {x.horaInicio = horaInicio});
      this.ausencias.map(x => {x.horaFin = horaFin});
    });
  }
}
