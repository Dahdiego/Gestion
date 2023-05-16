package com.example.manservic

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "ManServic"
        private const val DATABASE_VERSION = 1

        // Define los nombres de las tablas y columnas
        private const val TABLE_NAME = "partes"
        private const val COLUMNA_NPARTE = "n_parte"
        private const val COLUMNA_TECNICO = "tecnico"
        private const val COLUMNA_NCONTRATO = "n_contrato"
        private const val COLUMNA_CLIENTE = "cliente"
        private const val COLUMNA_TIPO_CONTRATO = "tipo_contrato"
        private const val COLUMNA_FECHA = "fecha"
        private const val COLUMNA_HORA_INICIO = "hora_inicio"
        private const val COLUMNA_HORA_FIN = "hora_fin"
        private const val COLUMNA_HORA_REALIZADAS = "hora_realizadas"
        private const val COLUMNA_HORA_DESCANSO = "hora_descanso"
        private const val COLUMNA_HORA_FACTURAR = "hora_facturar"
        private const val COLUMNA_HORA_DESPLAZAMIENTO = "hora_desplazamiento"
        private const val COLUMNA_LUGAR_REALIZADO = "lugar_realizado"
        private const val COLUMNA_TRABAJO_REALIZADO = "trabajo_realizado"
        private const val COLUMNA_DETALLES_MATERIAL = "detales_material"
        private const val COLUMNA_FIRMA = "firma"
    }

    private var queryCrearTabla1 = "CREATE TABLE IF NOT EXISTS tecnicos (" +
            "id integer primary key," +
            "email text," +
            "password text);"

    private var queryCrearTabla2 = "CREATE TABLE IF NOT EXISTS partes (" +
            "numero_parte integer primary key AUTOINCREMENT," +
            "tecnico text," +
            "numero_contrato text," +
            "cliente text," +
            "tipo_contrato text," +
            "fecha date," +
            "hora_inicio text," +
            "hora_fin text," +
            "hora_realizadas text," +
            "hora_descanso text," +
            "hora_facturar text," +
            "hora_desplazamiento text," +
            "lugar_realizado text," +
            "trabajo_realizado text," +
            "detalles_material text," +
            "firma text);"

    private var queryBorrarTabla1 = "DROP TABLE IF EXISTS tecnicos;"

    private var queryBorrarTabla2 = "DROP TABLE IF EXISTS partes;"



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(queryCrearTabla1)
        db.execSQL(queryCrearTabla2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(queryBorrarTabla1)
        db.execSQL(queryBorrarTabla2)
        onCreate(db)
    }




}