import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';
import { MaterialImportsModule } from './modules/material-imports.module';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AusenciasComponent } from './components/ausencias/ausencias.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { AusenciasUsuarioComponent } from './components/ausencias-usuario/ausencias-usuario.component';
import { CalendarioComponent } from './components/calendario/calendario.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { CommonModule } from '@angular/common';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { FlatpickrModule } from 'angularx-flatpickr';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { compareAsc, format } from 'date-fns';
import { DialogNuevaActividadComponent } from './components/dialog-nueva-actividad/dialog-nueva-actividad.component';
import { CalendarioUsuarioComponent } from './components/calendario-usuario/calendario-usuario.component';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AusenciasComponent,
    SidenavComponent,
    AusenciasUsuarioComponent,
    CalendarioComponent,
    DialogNuevaActividadComponent,
    CalendarioUsuarioComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    BrowserModule,
    AppRoutingModule,
    MaterialImportsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
