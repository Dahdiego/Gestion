package com.example.manservic

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class FuncionesSQL { // Fin clase Operaciones
    lateinit var context: Context
    lateinit var db: SQLiteDatabase

    fun init(context: Context) {
        this.context = context
    }

    fun open() {
        val helper = SQLiteHelper(context)
        db = helper.writableDatabase
    }

    fun close() {
        db.close()
    }

//    fun insertarUsuario (email: String, password: String) {
//        val query = "INSERT INTO users(email, password) VALUES ('$email', '$password')"
//        db.execSQL(query)
//    }

    // Controlar error en MainActivity: if (idNuevaFila != -1L)
    fun insertarUsuarioConInsert(email: String, password: String): Long {
        val nuevoRegistro = ContentValues()
        nuevoRegistro.put("email", email)
        nuevoRegistro.put("password", password)
        return db.insert("tecnicos", null, nuevoRegistro)
    }

    // Consultar si existe usuario
    fun consultarUsuarioExiste(email: String, password: String): Boolean {
        val sql = "SELECT * FROM tecnicos WHERE email = '$email' AND password = '$password'"
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            return true
        }

        // Otra opción:
//        if(cursor.count==0){
//            return false
//        }
//        return true

        cursor.close()
        return false
    }

    fun consultarEmailExiste(email: String): Boolean {
        val sql = "SELECT * FROM tecnicos WHERE email = '$email'"
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            return true
        }

        // Otra opción:
//        if(cursor.count==0){
//            return false
//        }
//        return true

        cursor.close()
        return false
    }


}