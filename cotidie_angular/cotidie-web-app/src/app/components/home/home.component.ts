import { Component, OnInit } from '@angular/core';
import { Listausuarios, UserBasicInfo } from 'src/app/models/listUsersResponse';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MatButton } from '@angular/material/button';
import { Router } from '@angular/router';
import { Actividad } from 'src/app/models/ausenciaListResponse';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  usuarios?: Listausuarios;
  descAct!: Actividad;

  constructor(
    private service: UsuarioService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.service.listarUsuarios().subscribe(res => {
      this.usuarios = res
    });
  }

  goToAusenciaUser(id: string): void{
    localStorage.setItem("usuario_id", id)
    this.router.navigateByUrl('/ausenciasUser')
  }

  goToVacacionUser(id: string): void{
    localStorage.setItem("usuario_id", id)
    this.router.navigateByUrl('/vacacionUser')
  }

  goToCalendarUser(id: string): void{
    localStorage.setItem("usuario_id", id)
    this.router.navigateByUrl('/calendarioUser')
  }

}
