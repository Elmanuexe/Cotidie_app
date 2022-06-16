import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { matDialogAnimations, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Actividad } from 'src/app/models/planificacionResponse';
import { AusenciaService } from 'src/app/services/ausencia.service';
import { CalendarioService } from 'src/app/services/calendario.service';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-dialog-nueva-actividad',
  templateUrl: './dialog-nueva-actividad.component.html',
  styleUrls: ['./dialog-nueva-actividad.component.css'],
})
export class DialogNuevaActividadComponent implements OnInit {
  actividad!: Actividad;
  actForm!: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: Actividad,
    private servicio: CalendarioService,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<DialogNuevaActividadComponent>
  ) {}

  ngOnInit(): void {
    this.servicio
      .getEvento(localStorage.getItem('evento_id')!)
      .subscribe((res) => {
        this.actividad = res;
      });
    this.actForm = this.formBuilder.group({
      fecha: [null, [Validators.required]],
      descripcion: [null, [Validators.required]],
      horaInicio: [null, [Validators.nullValidator]],
      horaFin: [null, [Validators.nullValidator]],
      todoElDia: [null, [Validators.nullValidator]],
    });
  }

  closeDialog(){
    this.dialogRef.close();
  }

  crear(): void {
    this.actividad = this.actForm.value
    this.servicio.crearActividad(localStorage.getItem('usuario_id')!, this.actividad).subscribe(res => {
      console.log(res)
    });
    console.log(this.actForm.value)
    //window.location.reload();
  }

  borrar(): void{
    if (this.actividad.tipo=='TRABAJO') {
      this.servicio.borrarActividad(localStorage.getItem('evento_id')!).subscribe()
      this.closeDialog()
    }
  }
}
