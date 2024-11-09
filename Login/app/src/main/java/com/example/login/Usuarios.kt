package com.example.login

data class Usuarios (
    var id_usuarios: Int,
    var nombre: String,
    var apPaterno: String,
    var apMaterno: String,
    var edad: String,
    var genero: String,
    var correo: String,
    var contrasena: String,
    var fechaNacimiento: String
)
data class LoginRequest(
    var correo: String,
    var contrasena: String
)
data class LoginResponse(
    val message: String,
    val usuarios: Usuarios?,
    val error: String
)