export interface Planificacion {
  fechaInicio: string
  fechaFin: any
  eventos: Actividad[]
  usuario: Usuario
}

export interface Actividad {
  id: string
  tipo: string
  fecha: Date
  descripcion: string
  horaInicio: string
  horaFin: string
  todoElDia: boolean
}

export interface Usuario {
  nombre: string
  apellidos: string
  fotoPerfil: string
  id: string
}
