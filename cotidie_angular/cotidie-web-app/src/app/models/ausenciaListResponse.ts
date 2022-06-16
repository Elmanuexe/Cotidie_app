export type AusenciaList = Actividad[]

export interface Actividad{
  tipo: string
  fecha: string
  descripcion: string
  horaInicio: any
  horaFin: any
  todoElDia: any
  usuario: Usuario
}

export interface Usuario {
  nombre: string
  apellidos: string
  fotoPerfil: string
  id: string
}
