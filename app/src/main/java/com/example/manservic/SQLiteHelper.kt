package com.example.manservic

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "ManServic"
        private const val DATABASE_VERSION = 1
    }

    private var queryCrearTabla = "CREATE TABLE IF NOT EXISTS tecnicos (" +
            "id integer primary key," +
            "email text," +
            "password text);"

    private var queryBorrarTabla = "DROP TABLE IF EXISTS tecnicos;"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(queryCrearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(queryBorrarTabla)
        onCreate(db)
    }

}