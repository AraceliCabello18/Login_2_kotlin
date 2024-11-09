package com.example.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.login.databinding.ActivityRegistroBinding
import kotlinx.coroutines.Dispatchers
import android.widget.TextView as TextView1

class Registro : AppCompatActivity() {

    private lateinit var registrar: Button
    private lateinit var cancelar: Button
    private lateinit var nombres: EditText
    private lateinit var apellidoPaterno: EditText
    private lateinit var apellidoMaterno: EditText
    private lateinit var edad: EditText
    private lateinit var fechaNacimiento: Button
    private lateinit var correo: EditText
    private lateinit var contrasena: EditText
    private lateinit var radioGenero: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        registrar = findViewById(R.id.button_Registrar)
        cancelar = findViewById(R.id.button_cancelar)
        nombres = findViewById(R.id.Nombres)
        apellidoPaterno = findViewById(R.id.apPaterno)
        apellidoMaterno = findViewById(R.id.apMaterno)
        edad = findViewById(R.id.Edad)
        fechaNacimiento = findViewById(R.id.FechaNacimiento)
        correo = findViewById(R.id.Correo)
        contrasena = findViewById(R.id.Contrasena)
        radioGenero = findViewById(R.id.radioGenero)

        registrar.setOnClickListener {

            val nombre = nombres.text.toString()
            val apellidoP = apellidoPaterno.text.toString()
            val apellidoM = apellidoMaterno.text.toString()
            val edadUsuario = edad.text.toString()
            val fechaNac = fechaNacimiento.text.toString()
            val correoUsuario = correo.text.toString()
            val contrasenaUsuario = contrasena.text.toString()
            val generoSeleccionadoId = radioGenero.checkedRadioButtonId

            val generoSeleccionado = when (generoSeleccionadoId) {
                R.id.radioMasculino -> "Masculino"
                R.id.radioFemenino -> "Femenino"
                R.id.radioOtro -> "Otro"
                else -> "No especificado"
            }
            FechaNacimiento setOnclikListener{
                showDatePickerDialog()
            }

            if(nombre.isNotEmpty() && apellidoP.isNotEmpty() && apellidoM.isNotEmpty()
                && edadUsuario.isNotEmpty() && fechaNac.isNotEmpty() && correoUsuario.isNotEmpty()
                && contrasenaUsuario.isNotEmpty()){

                val nuevoUsuario = Usuarios(
                    id_usuario = 0,
                    nombre = nombre,
                    apPaterno = apellidoP,
                    apMaterno = apellidoM,
                    edad = edadUsuario,
                    genero = generoSeleccionado,
                    correo = correoUsuario,
                    contrasena = contrasenaUsuario,
                    fechaNacimiento = fechaNac
                )

                registrarUsuario(nuevoUsuario)
            }else{
                Toast.makeText(this, "Por favor llene los campos", Toast.LENGTH_LONG).show()
            }

        }

        cancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
}
    private fun showDatePickerDialog(){
        val calendar = cancelar.getInstance()
        val year = calendar.get(calendar.YEAR)
        val month = calendar.get(calendar.MONTH)
        val day = calendar.get(calendar.DAY_OF_MONTH

            val datePickerDialog = DatePickerDialog(this, { , selectedYear, selectedMonth, selectedDay ->
            val formattecDate = simpleDateFormat('dd/mm/yy', locate.getDefault()).
            format(Date(year: selectedYear-1900,selectedMonth,selectedDay))
            fechaNacimiento.text = formattecDate
        },year,month,day)
        datePikerDialog.show()
    }
    private fun registrarUsuario(usuarios: Usuarios){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = RetrofitClient.webService.agregarUsuarios(usuarios)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        Toast.makeText(this@Registro_Activity, "Registro con Exito", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@Registro_Activity, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@Registro_Activity, "Fallo el registro", Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@Registro_Activity, "Error de conexion: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}