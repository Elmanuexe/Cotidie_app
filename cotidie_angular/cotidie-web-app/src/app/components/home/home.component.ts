import { Component, OnInit } from '@angular/core';
import { Listausuarios, UserBasicInfo } from 'src/app/models/listUsersResponse';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  usuarios?: Listausuarios;


  constructor(
    private service: UsuarioService
  ) { }

  ngOnInit(): void {
    this.service.listarUsuarios().subscribe(res => {
      this.usuarios = res
    });
  }
}
