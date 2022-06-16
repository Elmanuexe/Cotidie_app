import { Component, Input, OnInit } from '@angular/core';
import { Actividad, Planificacion } from 'src/app/models/planificacionResponse';
import { CalendarioService } from 'src/app/services/calendario.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogNuevaActividadComponent } from '../dialog-nueva-actividad/dialog-nueva-actividad.component';

export interface DialogData {
  tipo: string
  fecha: string
  descripcion: string
  horaInicio: any
  horaFin: any
  todoElDia: any
  usuarioId: string
}

@Component({
  selector: 'app-calendario-usuario',
  templateUrl: './calendario-usuario.component.html',
  styleUrls: ['./calendario-usuario.component.css']
})
export class CalendarioUsuarioComponent  implements OnInit {
  planificacion!: Planificacion;

  @Input() actividad!: Actividad;

  constructor(private service: CalendarioService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.service.fetchEventos(localStorage.getItem('usuario_id')!).subscribe((res) => {
      res.eventos.forEach((x) => {
        if (x.todoElDia) {
          x.horaFin = "00:00"
        } else{
          x.horaFin = x.horaFin.slice(11, 16);
        }
      });
      res.eventos.forEach((x) => {
        if (x.todoElDia) {
          x.horaInicio = "00:00"
        } else{
          x.horaInicio = x.horaInicio.slice(11, 16);
        }
      });
      this.planificacion = res;
    });
    throw new Error('Method not implemented.');
  }

  openNuevoDialog(){
    this.dialog.open(DialogNuevaActividadComponent, {
      height: '80vh',
      width: '50vw',
      data: {}
    })
  }

  openEditarDialog(id:string){
    localStorage.setItem("evento_id", id)
    this.dialog.open(DialogNuevaActividadComponent, {
      height: '80vh',
      width: '50vw',
      data: {}
    })
    window.location.reload
  }

}
