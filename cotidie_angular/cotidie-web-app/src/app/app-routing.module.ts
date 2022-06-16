import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AusenciasUsuarioComponent } from './components/ausencias-usuario/ausencias-usuario.component';
import { AusenciasComponent } from './components/ausencias/ausencias.component';
import { CalendarioUsuarioComponent } from './components/calendario-usuario/calendario-usuario.component';
import { CalendarioComponent } from './components/calendario/calendario.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'ausencias', component: AusenciasComponent},
  {path: 'ausenciasUser', component: AusenciasUsuarioComponent},
  {path: 'calendario', component: CalendarioComponent},
  {path: 'calendarioUser', component: CalendarioUsuarioComponent},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
