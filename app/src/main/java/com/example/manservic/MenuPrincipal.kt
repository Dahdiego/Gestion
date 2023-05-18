package com.example.manservic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.manservic.databinding.ActivityMenuprincipalBinding

class MenuPrincipal  : AppCompatActivity()  {
    // 1. Configuramos viewBinding
    private lateinit var binding: ActivityMenuprincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuprincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAtras.setOnClickListener {
            finish()
        }
        binding.btPartes.setOnClickListener {
            val intent = Intent(this@MenuPrincipal, IntroducirPartes::class.java)
            startActivity(intent)
        }
        binding.btHistorial.setOnClickListener {

        }
    }
}