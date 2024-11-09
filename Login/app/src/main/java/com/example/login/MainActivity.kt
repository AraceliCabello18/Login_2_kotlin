package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import java.util.Hashtable


class MainActivity : AppCompatActivity() {
    private lateinit var registro: Button
    private lateinit var loguear: Button
    private lateinit var correo: EditText
    private lateinit var contrasena: EditText


    @SuppressLint("MissingInflatedId")
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViesCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            correo = findViewById(com.example.login.R.id.Correo)
            contrasena = findViewById(com.example.login.R.id.Contrasena)
            registro = findViewById(com.example.login.R.id.registro)
            loguear = findViewById(com.example.login.R.id.loguear)

            loguear.setOnClickListener {
                val Email = correo.text.toString()
                val Password = contrasena.text.toString()

                if (Email.isNotEmpty() && Password.isEmpty()) {
                    loginUsuario(Email, Password)
                } else {
                    Toast.makeText(this, "Ingresa los campos", Toast.LENGTH_SHORT).Show()
                }
            }
            registro.setOnClickListener {
                val intent = intent(this, activity_registro::class.java)
                startActivity(intent)
            }
            insert
        }
    }

    private fun loginUsuario(Email: String, Password: String) {
        inRequest = loginRequest(correo = Email, contrasena = Password)
        noScape(Dispatchers.IO).launch {

            val response: Response<LoginResponse> = RetrofitClient.webService.login(LoginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse?.usuario != null) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Login Exitoso", Toast.LENGTH_SHORT)
                            .show()
                        val intent = intent(this@MainActivity, activity_main::class.java)
                        startActivity(intent)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "Credenciales Incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Error de Conexion", Toast.LENGTH_SHORT)
                        .show()

                }
            }catch (e:Exception){

            runOnUiThread {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        }
    }


