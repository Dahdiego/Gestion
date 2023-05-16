package com.example.manservic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import com.example.manservic.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // 1. Configuramos viewBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var funciones: FuncionesSQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la base de datos
        funciones = FuncionesSQL()
        // Le pasamos a la clase operaciones el contexto
        funciones.init(applicationContext)
        //Abrimos la base de datos
        funciones.open()

        binding.btLogin.setOnClickListener {
            // Campos correctos y usuario existe en base de datos dbLogin... Abrimos 2ª activity
            if (validarDatos(binding.etEmail, binding.etPassword)) {
                if (buscarUsuarioEnDb(binding.etEmail, binding.etPassword)) {
                    val intent = Intent(this@MainActivity, MenuPrincipal::class.java)
                    intent.putExtra("EMAIL", binding.etEmail.text.toString())
                    intent.putExtra("PASS", binding.etPassword.text.toString())
                    startActivity(intent)
                } else {
                    mostrarSnackbar("Comprueba tus datos (¿contraseña correcta?). " +
                            "Pulsa el botón Registro si aún no estás dado de alta")
                }
            }
        }
        binding.btRegistro.setOnClickListener {
            if (validarDatos(binding.etEmail, binding.etPassword)) {
                if (!buscarEmailEnDb(binding.etEmail)) {  // Si no existe el correo en la bdd
                    val resultado = funciones.insertarUsuarioConInsert(binding.etEmail.text.toString(),
                        binding.etPassword.text.toString())
                    if (resultado != -1L) {
                        mostrarSnackbar("Insertado usuario con id $resultado")
                    } else {
                        mostrarSnackbar("Error desconocido al insertar usuario")
                    }
                } else {  // Si ya existe, se muestra Snackbar
                    mostrarSnackbar("Fallo en registro. Ya existe un usuario registrado con ese email")
                }
            } else {
                mostrarSnackbar("Datos inválidos")
            }
        }

    } // Fin onCreate

    private fun validarDatos(
        email: EditText,
        pass: EditText
    ): Boolean {

        val a = validarEmail(email)
        val b = validarPassword(pass)

        return a && b
    }

    private fun buscarUsuarioEnDb(
        email: EditText,
        pass: EditText
    ): Boolean {
        val valorCampoEmail = email.text.toString()
        val valorCampoPass = pass.text.toString()
        return funciones.consultarUsuarioExiste(valorCampoEmail, valorCampoPass)
    }

    private fun buscarEmailEnDb(
        email: EditText,
    ): Boolean {
        val valorCampoEmail = email.text.toString()
        return funciones.consultarEmailExiste(valorCampoEmail)
    }

    private fun validarEmail(etEmail: EditText): Boolean {
        etEmail.error = null
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches())
        {
            etEmail.error = "Correo electrónico inválido"
            return false
        } else {
            etEmail.error =null
        }
        return true
    }

    private fun validarPassword(etPass: EditText): Boolean {
        etPass.error = null
        if (etPass.text.toString().isNullOrEmpty()) {
            etPass.error = "El campo password no puede estar vacío"
            return false
        }
        return true
    }

    private fun mostrarSnackbar(mensaje: String) {
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG)
            .show()
    }

} // Fin clase MainActivity