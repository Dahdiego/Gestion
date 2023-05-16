package com.example.manservic

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.manservic.databinding.ActivityPartesBinding
import java.text.SimpleDateFormat
import java.util.*


class IntroducirPartes : AppCompatActivity() {

    private lateinit var binding: ActivityPartesBinding
    private lateinit var funciones: FuncionesSQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la base de datos
        funciones = FuncionesSQL()
        // Le pasamos a la clase operaciones el contexto
        funciones.init(applicationContext)
        //Abrimos la base de datos
        funciones.open()




        val email = "prueba@manservic.es"

        val parts = email.split("@")
        if (parts.size > 1) {
            val username = parts[0]
            binding.tvtecnico.text = username

        }
        val edFecha = findViewById<TextView>(R.id.etFecha)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.format(Date())
        edFecha.text = currentDate



            binding.etHoraI.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val input = s.toString()

                    if (!isValidTimeFormat(input)) {
                        binding.etHoraI.error = "Formato incorrecto (ejemplo: 00:00)"
                    } else {
                        binding.etHoraI.error = null
                    }
                }

                private fun isValidTimeFormat(input: String): Boolean {
                    val regex = Regex("^([01]\\d|2[0-3]):([0-5]\\d)$")
                    return input.matches(regex)
                }
            })



        binding.etHoraF.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()

                if (!isValidTimeFormat(input)) {
                    binding.etHoraF.error = "Formato incorrecto (ejemplo: 00:00)"
                } else {
                    binding.etHoraF.error = null
                }
            }

            private fun isValidTimeFormat(input: String): Boolean {
                val regex = Regex("^([01]\\d|2[0-3]):([0-5]\\d)$")
                return input.matches(regex)
            }
        })

        binding.huecofirma.setOnTouchListener { _, event ->
            // Bloquear el ScrollView
            binding.scrollPantalla.requestDisallowInterceptTouchEvent(true)

            // Manejar la firma
            binding.canvasView.onTouchEvent(event)
        }
    }//Find del oncreate

    private fun insertarRegistro() {



            val resultado = funciones.insertarDatos(
                binding.tvnparte.text.toString(),
                binding.tvtecnico.text.toString(),
                binding.spncontrato.selectedItem.toString().toInt(),
                binding.etCliente.text.toString(),
                binding.spTipoContrato.selectedItem.toString(),
                binding.etFecha.text.toString(),
                binding.etHoraI.text.toString(),
                binding.etHoraF.text.toString(),
                binding.tvHorasR.text.toString(),
                binding.etHorad.text.toString().toInt(),
                binding.etHorasf.text.toString().toInt(),
                binding.edHorasD.text.toString(),
                binding.spLugar.selectedItem.toString(),
                binding.etTrabajos.text.toString(),
                binding.etDetalles.text.toString(),
                //binding.huecofirma.boolean.toString()
                )
                Toast.makeText(
                    applicationContext,
                    "Nuevo registro insertado con ID: $resultado",
                    Toast.LENGTH_SHORT
                ).show()
                // Si nos devuelve -1 es que ha habido algún error al insertar
                Toast.makeText(
                    applicationContext,
                    "Error al insertar",
                    Toast.LENGTH_SHORT
                ).show()
    } // Fin función insertarRegistro()

}