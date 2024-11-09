package com.example.login

import android.os.Bundle
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.login.databinding.ActivityActualizarBinding

class Actualizar : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityActualizarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)

        val idUsuario = intent.getIntExtra( "id_usuario", -1)
        val nombre = intent.getIntExtra("nombre")
        val apPaterno = intent.getIntExtra("apPaterno")
        val apMaterno = intent.getIntExtra("apMaterno")
        val edad = intent.getIntExtra("edad")
        val genero = intent.getIntExtra("genero")
        val correo = intent.getIntExtra("correo")
        val contrasena = intent.getIntExtra("contrasena")
        val fechaNacimiento = intent.getIntExtra("fechaNacimiento")

        val editarTextNombre = findViewById<EditText>(R.id.Nombre)
        val editarTextApPaterno = findViewById<EditText>(R.id.apPaterno)
        val editarTextApMaterno = findViewById<EditText>(R.id.apMaterno)
        val editarTextEdad = findViewById<EditText>(R.id.Edad)
        val editarTextGenero = findViewById<EditText>(R.id.genero)
        val editarTextCorreo = findViewById<EditText>(R.id.Correo)
        val editarTextContrasena = findViewById<EditText>(R.id.Contrasena)
        val editarTextFechaNacimiento = findViewById<EditText>(R.id.FechaNacimiento)


    }


}