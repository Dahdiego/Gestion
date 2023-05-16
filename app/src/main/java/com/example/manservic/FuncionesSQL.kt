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

    fun insertarDatos(
        nParte: String, tecnico: String, nContrato: Int, cliente: String,
        tipo_contrato: String, fecha: String, hora_inicio: String, hora_fin: String,
        hora_realizadas: String, hora_descanso: Int, hora_facturar: Int, hora_desplazamiento: String,
        lugar_realizado: String, trabajo_realizado: String, detalles_material: String, /**firma: String**/) {

        val values = ContentValues()
        values.put("numero_partes", nParte)
        values.put("tecnico", tecnico)
        values.put("numero_contrato", nContrato)
        values.put("cliente", cliente)
        values.put("tipo_contrato", tipo_contrato)
        values.put("fecha", fecha)
        values.put("hora_inicio", hora_inicio)
        values.put("hora_fin", hora_fin)
        values.put("hora_realizadas", hora_realizadas)
        values.put("hora_descanso", hora_descanso)
        values.put("hora_facturar", hora_facturar)
        values.put("hora_desplazamiento", hora_desplazamiento)
        values.put("lugar_realizado", lugar_realizado)
        values.put("trabajo_realizado", trabajo_realizado)
        values.put("detalles_material", detalles_material)
        //values.put("firma", firma)

        db.insert("partes", null, values)
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