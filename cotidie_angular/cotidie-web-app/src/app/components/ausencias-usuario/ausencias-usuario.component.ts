import { Component, OnInit } from '@angular/core';
import { AusenciaList } from 'src/app/models/ausenciaListResponse';
import { AusenciaService } from 'src/app/services/ausencia.service';

@Component({
  selector: 'app-ausencias-usuario',
  templateUrl: './ausencias-usuario.component.html',
  styleUrls: ['./ausencias-usuario.component.css']
})
export class AusenciasUsuarioComponent implements OnInit {
  ausencias?: AusenciaList;

  constructor(private service: AusenciaService) { }

  ngOnInit(): void {
    this.service.fetchAusenciasUsuario(localStorage.getItem("usuario_id")!).subscribe((res) => {
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
