package com.example.manservic

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class IntroducirPartes : AppCompatActivity() {

    private lateinit var funciones: FuncionesSQL
    private lateinit var edHoraI: EditText
    private lateinit var edHoraF: EditText
    private lateinit var tvHorasT: TextView
    private lateinit var tvTecnico: TextView
    private lateinit var edFecha: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partes)

        // Inicializamos la base de datos
        funciones = FuncionesSQL()
        // Le pasamos a la clase operaciones el contexto
        funciones.init(applicationContext)
        //Abrimos la base de datos
        funciones.open()
        val spinnerLugar = findViewById<Spinner>(R.id.spLugar)



        val email = "prueba@manservic.es"
        val tvTecnico = findViewById<TextView>(R.id.tvtecnico)

        val parts = email.split("@")
        if (parts.size > 1) {
            val username = parts[0]
            tvTecnico.text = username

        }
        val edFecha = findViewById<TextView>(R.id.edFecha)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.format(Date())
        edFecha.text = currentDate

        val etHoraI = findViewById<EditText>(R.id.etHoraI)

            etHoraI.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val input = s.toString()

                    if (!isValidTimeFormat(input)) {
                        etHoraI.error = "Formato incorrecto (ejemplo: 00:00)"
                    } else {
                        etHoraI.error = null
                    }
                }

                private fun isValidTimeFormat(input: String): Boolean {
                    val regex = Regex("^([01]\\d|2[0-3]):([0-5]\\d)$")
                    return input.matches(regex)
                }
            })

        val etHoraF = findViewById<EditText>(R.id.etHoraF)

        etHoraF.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()

                if (!isValidTimeFormat(input)) {
                    etHoraF.error = "Formato incorrecto (ejemplo: 00:00)"
                } else {
                    etHoraF.error = null
                }
            }

            private fun isValidTimeFormat(input: String): Boolean {
                val regex = Regex("^([01]\\d|2[0-3]):([0-5]\\d)$")
                return input.matches(regex)
            }
        })
        val scroll: ScrollView = findViewById(R.id.scrollPantalla)
        val firma: LinearLayout = findViewById(R.id.huecofirma)
        val canvasView: CanvasView = findViewById(R.id.canvasView)
        firma.setOnTouchListener { _, event ->
            // Bloquear el ScrollView
            scroll.requestDisallowInterceptTouchEvent(true)

            // Manejar la firma
            canvasView.onTouchEvent(event)
        }

    }//Find del oncreate


}