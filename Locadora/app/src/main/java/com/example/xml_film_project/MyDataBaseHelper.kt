package com.example.xml_film_project

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(context: Context) : SQLiteOpenHelper(context, "BancoFilme", null, 1){

    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabela = "Filme"
        val id = "id"
        val titulo = "titulo"
        val custo = "custo"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} (" +
                    "${id} INTEGER PRIMARY KEY," +
                    "${titulo} TEXT," +
                    "${custo} TEXT)"
        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS Filme"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }

}
