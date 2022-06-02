export interface LoginResponse {
  id: string
  nick: string
  nombre: string
  apellidos: string
  rol: string
  fotoPerfil: string
  tokenJwt: string
}

export interface LoginDTO {
  nick: string
  password: string
}
